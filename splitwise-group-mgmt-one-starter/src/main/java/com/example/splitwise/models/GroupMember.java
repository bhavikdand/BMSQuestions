package com.example.splitwise.models;


import java.util.Date;

public class GroupMember extends BaseModel{
    private Group group;
    private User user;
    private User addedBy;
    private Date addedAt;
}
