package com.hh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Message {

  private int messageId;
  private int user1Id;
  private int user2Id;
  private String message;
  private java.sql.Timestamp time;
}
