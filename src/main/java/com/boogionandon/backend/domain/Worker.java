package com.boogionandon.backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("WORKER")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Worker extends Member{

  @Column(length = 20, nullable = false)
  private String contact; // 근무처 연락처, 근무처? 가 중복일 수도 있을거 같아서 unique 안걸음

  @Column(length = 100)
  private String workGroup; // 소속, TODO : 이건 뭐에대한 소속이지?

  @Column(length = 100)
  private String workAddress; //// 소속 주소

  @Column(length = 150)
  private String workAddressDetail; // 소속 상세 주소

  @Column
  private int vehicleCapacity; // 차량정보(무게 ton)





  // 추가로 필요한 필드가 있다면 추가

}