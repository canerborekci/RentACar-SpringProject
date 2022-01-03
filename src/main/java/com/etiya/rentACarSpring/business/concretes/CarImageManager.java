package com.etiya.rentACarSpring.business.concretes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.etiya.rentACarSpring.business.abstracts.LanguageWordService;
import com.etiya.rentACarSpring.business.constants.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.etiya.rentACarSpring.business.abstracts.CarImageService;
import com.etiya.rentACarSpring.business.abstracts.CarService;
import com.etiya.rentACarSpring.business.dtos.CarImageDto;
import com.etiya.rentACarSpring.business.dtos.CarImageListDto;
import com.etiya.rentACarSpring.business.requests.carImages.CreateCarImagesRequest;
import com.etiya.rentACarSpring.business.requests.carImages.DeleteCarImageRequest;
import com.etiya.rentACarSpring.business.requests.carImages.UpdateCarImageRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.ErrorDataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.ErrorResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;
import com.etiya.rentACarSpring.core.utilities.mapping.results.SuccessDataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.SuccessResult;
import com.etiya.rentACarSpring.dataAccess.abstracts.CarImageDao;
import com.etiya.rentACarSpring.entities.CarImage;

@Service
public class CarImageManager implements CarImageService {
    private CarImageDao carImageDao;
    private ModelMapperService modelMapperService;
    private CarService carService;
    private LanguageWordService languageWordService;


    @Autowired
    public CarImageManager(CarImageDao carImageDao, ModelMapperService mapperService, @Lazy CarService carService, LanguageWordService languageWordService) {
        this.carImageDao = carImageDao;
        this.modelMapperService = mapperService;
        this.carService = carService;
        this.languageWordService = languageWordService;
    }


    @Override
    public Result add(CreateCarImagesRequest createCarImagesRequest) throws IOException {
        Result result = BusinessRules.run(checkCarImageCounter(createCarImagesRequest.getCarId()), carService.existsByCarId(createCarImagesRequest.getCarId()));
        if (result != null) {
            return result;
        }

        Date dateNow = new java.sql.Date(new java.util.Date().getTime());
        CarImage carImage = modelMapperService.forRequest().map(createCarImagesRequest, CarImage.class);
        carImage.setImagePath(uploadImage(createCarImagesRequest.getFile()).toString());
        carImage.setDate(dateNow);
        this.carImageDao.save(carImage);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.CARIMAGEADD).getData());
    }

    @Override
    public Result update(UpdateCarImageRequest updateCarImageRequest) throws IOException {
        Result result = BusinessRules.run(existsByCarImageId(updateCarImageRequest.getId()), carService.existsByCarId(updateCarImageRequest.getCarId()));
        if (result != null) {
            return result;
        }
        Date dateNow = new java.sql.Date(new java.util.Date().getTime());
        CarImage toBeDeleted = this.carImageDao.getById(updateCarImageRequest.getId());
        deleteImage(toBeDeleted.getImagePath());

        CarImage carImage = modelMapperService.forRequest().map(updateCarImageRequest, CarImage.class);
        carImage.setImagePath(uploadImage(updateCarImageRequest.getFile()).toString());
        carImage.setDate(dateNow);
        this.carImageDao.save(carImage);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.CARIMAGEUPDATE).getData());
    }


    @Override
    public Result delete(DeleteCarImageRequest deleteCarImagesRequest) {
        Result result = BusinessRules.run(existsByCarImageId(deleteCarImagesRequest.getId()));
        if (result != null) {
            return result;
        }
        CarImage carImage = this.carImageDao.getById(deleteCarImagesRequest.getId());

        this.carImageDao.delete(carImage);

        deleteImage(carImage.getImagePath());
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.CARIMAGEDELETE).getData());
    }

    @Override
    public DataResult<List<CarImageListDto>> getAll() {
        List<CarImage> carImages = this.carImageDao.findAll();
        List<CarImageListDto> result = carImages.stream()
                .map(carImage -> modelMapperService.forDto().map(carImage, CarImageListDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<CarImageListDto>>(result, this.languageWordService.getValueByKey(Messages.CARIMAGELIST).getData());
    }


    @Override
    public DataResult<List<CarImageDto>> getImageByCarId(int carId) {


        if (!carService.existsByCarId(carId).isSuccess()) {
            return new ErrorDataResult<List<CarImageDto>>(null, this.languageWordService.getValueByKey(Messages.CARNOTFOUND).getData());
        }

        int counter = this.carImageDao.countCarImageByCar_Id(carId);

        if (counter == 0) {
            CarImageDto carImage = new CarImageDto();
            carImage.setImagePath("C:\\Users\\caner.borekci\\Desktop\\rentACarSpring\\rentACarSpring\\Images\\default.jpg");
            Date dateNow = new java.sql.Date(new java.util.Date().getTime());
            carImage.setDate(dateNow);
            List<CarImageDto> carImages = new ArrayList<CarImageDto>();
            carImages.add(carImage);
            return new SuccessDataResult<List<CarImageDto>>(carImages, this.languageWordService.getValueByKey(Messages.CARIMAGELIST).getData());
        }


        List<CarImage> carImages = this.carImageDao.findAllByCarId(carId);
        List<CarImageDto> result = carImages.stream()
                .map(carImage -> modelMapperService.forDto().map(carImage, CarImageDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<CarImageDto>>(result, this.languageWordService.getValueByKey(Messages.CARIMAGELIST).getData());

    }

    private Result existsByCarImageId(int carImageId) {
        boolean result = this.carImageDao.existsById(carImageId);
        if (result == false) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.CARIMAGENOTFOUND).getData());
        }
        return new SuccessResult();
    }

    private File uploadImage(MultipartFile file) throws IOException {
        String randomImageName = java.util.UUID.randomUUID().toString();

        File myFile = new File("C:\\Users\\caner.borekci\\Desktop\\rentACarSpring\\rentACarSpring\\Images" + "\\" + randomImageName + "."
                + file.getContentType().toString().substring(file.getContentType().indexOf("/") + 1));
        myFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(myFile);
        fos.write(file.getBytes());
        fos.close();

        return myFile;
    }

    private Result deleteImage(String imagePath) {
        if (!imagePath.isEmpty()) {

            File willBeDeletedImage = new File(imagePath);
            willBeDeletedImage.delete();
        }

        return new SuccessResult();
    }

    private Result checkCarImageCounter(int carId) {
        int counter = this.carImageDao.countCarImageByCar_Id(carId);
        if (counter >= 5) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.CARIMAGELIMIT).getData());
        }
        return new SuccessResult();
    }


}


	




