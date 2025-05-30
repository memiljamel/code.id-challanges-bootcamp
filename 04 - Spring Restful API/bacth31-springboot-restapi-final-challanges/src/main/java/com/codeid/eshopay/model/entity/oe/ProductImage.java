package com.codeid.eshopay.model.entity.oe;

import com.codeid.eshopay.model.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_images", schema = "oe")
public class ProductImage extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_seq")
    @SequenceGenerator(name = "image_seq", sequenceName = "oe.product_images_image_id_seq", allocationSize = 1)
    @Column(name = "image_id")
    private Short imageId;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "file_type", length = 50)
    private String fileType;

    @Column(name = "file_uri", length = 100)
    private String fileUri;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;
}
