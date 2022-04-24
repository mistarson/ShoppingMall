package myproject.shoppingmall.auditing;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class BaseByEntity extends BaseEntity {
    @CreatedBy
    @Column(updatable = false)
    private String created_by;

    @LastModifiedBy
    private String modified_by;
}
