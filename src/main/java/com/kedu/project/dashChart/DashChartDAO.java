package com.kedu.project.dashChart;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DashChartDAO {
    @Autowired
    private SqlSession mybatis;

    public int getCountByPath(String path){
        return mybatis.selectOne("dashcart.getCountByPath", path);
    }

    public int countUpdate(DashChartDTO dto){
        return mybatis.update("dashcart.countUpdate", dto);
    }
}
