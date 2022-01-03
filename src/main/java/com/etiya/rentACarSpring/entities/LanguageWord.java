package com.etiya.rentACarSpring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "language_words")
public class LanguageWord {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "key_id")
    private Key key;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @Column(name = "translation")
    private String translation;
}
