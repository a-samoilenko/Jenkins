package models;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Pet {
    private Long id;
    private Category category;
    private String name;
    private ArrayList<String> photoUrls;
    private ArrayList<Tag> tags;
    private String status;
}
