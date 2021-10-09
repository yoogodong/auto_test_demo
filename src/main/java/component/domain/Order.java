package component.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="STORE_ORDER")
@EntityListeners(AuditingEntityListener.class)
public class Order {
    @Id @GeneratedValue
    private Long id;
    @CreatedDate
    private long createdTime;

    private String shipmentAddress;
    private String receiverName;
    private String receiverPhone;

}
