package com.etiya.rentACarSpring.dataAccess.abstracts;

import com.etiya.rentACarSpring.entities.Invoice;
import com.etiya.rentACarSpring.entities.complexTypes.UsersInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface InvoiceDao extends JpaRepository<Invoice,Integer> {


    @Query("select new com.etiya.rentACarSpring.entities.complexTypes.UsersInvoice "
    +" (u.id, i.invoiceNo, i.totalPrice, r.id) "
            +" from User u inner join u.rentals r inner join r.invoice i where u.id = ?1"
    )
    List<UsersInvoice> getUsersInvoices(int userId);

    List<Invoice> findByCreationDateBetween(LocalDate beginDate, LocalDate endDate);

    boolean existsById(int invoiceId);

    Invoice getByRental_Id(int rentalId);
}
