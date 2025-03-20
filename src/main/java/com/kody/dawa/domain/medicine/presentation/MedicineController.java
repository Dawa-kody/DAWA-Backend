package com.kody.dawa.domain.medicine.presentation;

import com.kody.dawa.domain.medicine.presentation.dto.MedicineDeleteRequest;
import com.kody.dawa.domain.medicine.presentation.dto.MedicineGetRequest;
import com.kody.dawa.domain.medicine.presentation.dto.MedicineRequest;
import com.kody.dawa.domain.medicine.presentation.dto.MedicineUpdateRequest;
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
        try {
            return ResponseEntity.ok(medicineService.updateMedicine(request));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete (@RequestBody MedicineDeleteRequest request) {
        try{
            medicineService.deleteMedicine(request);
            return ResponseEntity.ok("success");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/get")
    public ResponseEntity<?> get (@RequestBody MedicineGetRequest request) {
        try{
            return ResponseEntity.ok(medicineService.getAllMedicine(request));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
