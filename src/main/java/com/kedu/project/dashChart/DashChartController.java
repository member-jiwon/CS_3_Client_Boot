package com.kedu.project.dashChart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/dashCart")
@RestController
public class DashChartController {
    @Autowired
    private DashChartService dashChartService;

    @PostMapping
    public ResponseEntity<Void> countUpdate(@RequestBody DashChartDTO dto) {
        dashChartService.countUpdate(dto);
        return ResponseEntity.ok().build();
    }
}
