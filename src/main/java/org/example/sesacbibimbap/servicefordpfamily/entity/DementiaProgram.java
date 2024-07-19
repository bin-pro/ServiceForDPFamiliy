package org.example.sesacbibimbap.servicefordpfamily.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "dementia_programs")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
public class DementiaProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dementia_center_id",
            nullable = false,
            columnDefinition = "BIGINT",
            foreignKey = @ForeignKey(name = "fk_dementia_programs_dementia_center"))
    DementiaCenter dementiaCenter;

    @Builder
    public DementiaProgram(String name) {
        this.name = name != null ? name : "";
    }

    public void setDementiaCenter(DementiaCenter center) {
        this.dementiaCenter = center;
    }
}
