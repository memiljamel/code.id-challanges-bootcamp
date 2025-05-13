package com.codeid.oe.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
@EntityListeners({
        AuditingEntityListener.class
})
public abstract class AbstractEntity {

    @CreatedDate
    @Column(name = "created_date", nullable = false)
    private Instant createDate;

    @LastModifiedDate
    @Column(name = "modified_date")
    private Instant modifiedDate;
}
