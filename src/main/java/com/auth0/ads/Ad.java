package com.auth0.ads;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;


@Entity
    @Getter
    @Setter
    @EqualsAndHashCode
    public class Ad {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)

        public Long id;

        public String owner;
        public String title;
        public String description;
        public BigDecimal price;

        public Ad( ) {

        }

        public Ad(Long id,String owner, String title, String description, BigDecimal price) {
            this.owner = owner;
            this.title = title;
            this.description = description;
            this.price = price;
            this.id=id;
        }
    }


