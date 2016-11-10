package com.sav.bean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
 
@ManagedBean(name = "accueilBean")
public class  AccueilBean {
     
    private List<String> images;
     
    @PostConstruct
    public void init() {
    	
    	
        images = new ArrayList<String>();
        images.add("savimg10.jpg");
        images.add("savimg7.jpg");
        images.add("savimg6.png");
        images.add("savimg1.jpg");
        images.add("savimg11.jpg");
       
    }
 
    public List<String> getImages() {
        return images;
    }
}