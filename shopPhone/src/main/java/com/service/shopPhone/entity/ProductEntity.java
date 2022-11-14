package com.service.shopPhone.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "products")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
public class ProductEntity {
    
    @Id
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "price")
    private Double price;

    @Column(name = "status")
    private String status;

    @Column(nullable = false, name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "description")
    private String description;

    @Column(name = "screen")
    private String screen;

    @Column(name = "operating_system")
    private String operatingSystem;

    @Column(name = "chip")
    private String chip;

    @Column(name = "ram")
    private String ram;

    @Column(name = "internal_memory")
    private String internalMemory;

    @Column(name = "rear_camera")
    private String rearCamera;

    @Column(name = "front_camera")
    private String frontCamera;

    @ManyToOne
    @JoinColumn(name="brand_id", referencedColumnName = "id")
    private BrandEntity brand;

    @Column(name = "quantity")
    private int quantity;
}
