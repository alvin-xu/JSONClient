package cn.redarmy.service;
import java.util.List;

import cn.redarmy.domain.*;

public interface GoodService {
	public abstract List<Good> findAll();   
	  
    public abstract Good findById();   
}
