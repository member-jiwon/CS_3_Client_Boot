package com.kedu.project.growth_chart;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kedu.project.baby.BabyDTO;
import com.kedu.project.baby.BabyService;

@RequestMapping("/chart")
@RestController
public class GrowthChartController {

	@Autowired
	private GrowthChartService growthChartService;

	@Autowired
	private BabyService babyService;

	@GetMapping("/{babySeq}")
	public ResponseEntity<BabyDTO> getBabyInfoForChart(@PathVariable int babySeq) {
		// BabyService를 호출하여 BabyDTO (status, birthDate) 반환
		// Service는 int를 사용하므로 Long으로 변환할 필요 없음
		BabyDTO babyInfo = babyService.getBabyInfo(babySeq); 
		if (babyInfo == null) {
			//  DB에 아기가 없으면 404 NOT FOUND를 반환하여 안전하게 처리합니다.
			return ResponseEntity.notFound().build(); 
		}
		// 200 OK와 함께 BabyDTO를 반환
		return ResponseEntity.ok(babyInfo);
	}

	@GetMapping("/total") 
	public ResponseEntity<Map<String, Object>> getTotalChartData(
			@RequestParam int babyId
			,@RequestParam int week
			){
		try {
			// Service를 호출하여 babySeq 조회, 주차 계산, 해당 주차의 실측치 조회를 한 번에 수행합니다.
			Map<String, Object> resultData = growthChartService.getTotalChart(babyId, week);

			return ResponseEntity.ok(resultData);

		} catch (Exception e) {
			// 오류 발생 시 500 Internal Server Error 반환
			return ResponseEntity.internalServerError().body(Map.of("error", e.getMessage()));
		}
	}
	
	@GetMapping("/history/{babySeq}")
    public ResponseEntity<List<MeasurementGroupedResponse>> getHistoricalChartData(@PathVariable int babySeq) {
        
        try {
            // Service를 호출하여 모든 기록을 조회하고 주차 계산 및 가공을 수행합니다.
            List<MeasurementGroupedResponse> data = growthChartService.getAllHistoricalMeasurements(babySeq);
            
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

}
