package com.kedu.project.dashChart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashChartService {
    @Autowired
    private DashChartDAO dao;

    public int countUpdate(DashChartDTO dto){
        int count = dao.getCountByPath(dto.getPath());
        dto.setCount(count+1);
        return dao.countUpdate(dto);
    }
}
