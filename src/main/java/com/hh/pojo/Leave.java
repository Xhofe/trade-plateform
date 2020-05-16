package com.hh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Leave {

  private int leaveId;
  private int userId;
  private int goodsId;
  private String leaveMessage;
  private java.sql.Timestamp leaveTime;
}
