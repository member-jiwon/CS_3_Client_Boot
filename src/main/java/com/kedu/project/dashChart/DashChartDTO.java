package com.kedu.project.dashChart;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DashChartDTO {
    private int dash_cart_seq; // 필요한가? 혹시몰라서 
    private String path_name; // 페이지이름
    private String path; // path값
    private int count;
}
