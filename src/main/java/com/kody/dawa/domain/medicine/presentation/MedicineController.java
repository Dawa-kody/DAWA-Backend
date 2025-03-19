package com.kody.dawa.domain.medicine.presentation;


import com.kody.dawa.domain.medicine.presentation.domain.MedicineRequest;;
import com.kody.dawa.domain.medicine.presentation.domain.MedicineUpdateRequest;
import com.kody.dawa.domain.medicine.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/medicine")
public class MedicineController {

    private final MedicineService medicineService;

    @PostMapping("/insert")
    public ResponseEntity<?> insert (@RequestBody MedicineRequest request) {
        try {
            return ResponseEntity.ok(medicineService.createMedicine(request));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update (@RequestBody MedicineUpdateRequest request) {
        return ResponseEntity.ok(medicineService.updateMedicine(request));
    }
}
