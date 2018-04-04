package com.zc.mass.service.impl;

import com.zc.mass.service.MassService;
import com.zc.mass.util.MassExcelUtil;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service("massServiceImpl")
public class MassSeviceImpl implements MassService{

    @Override
    public void mass(InputStream inputStream) {
        List<Map<String,Object>> listmap =  MassExcelUtil.disposeExcel(inputStream);
    }

}
