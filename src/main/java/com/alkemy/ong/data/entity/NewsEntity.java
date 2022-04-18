package com.alkemy.ong.data.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="news")
@Getter
@Setter
@SQLDelete(sql = "UPDATE news set is_deleted = true where id = ?")
@Where(clause = "is_deleted = false")
public class NewsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false, columnDefinition="TEXT")
    private String content;
    @Column(nullable = false)
    private String image;

    /*@ManyToOne(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private CategoryEntity category;*/

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name="is_deleted", nullable = false)
    private boolean isDeleted = Boolean.FALSE;

    @CreationTimestamp
    @Column(name="created_at", columnDefinition = "TIMESTAMP", updatable = false, nullable = false)
    @DateTimeFormat(pattern = "yyyy/MM/dd hh:mm:ss")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name="updated_at", columnDefinition = "TIMESTAMP")
    @DateTimeFormat(pattern = "yyyy/MM/dd hh:mm:ss")
    private LocalDateTime updatedAt;
}
