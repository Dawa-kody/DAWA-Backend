package com.kody.dawa.domain.medicine.presentation;

import com.kody.dawa.domain.medicine.presentation.dto.MedicineRequest;
import com.kody.dawa.domain.medicine.presentation.dto.MedicineUpdateRequest;
import com.kody.dawa.domain.medicine.service.MedicineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/medicine")
public class MedicineController {

    private final MedicineService medicineService;

    @PostMapping("/insert") //admin
    public ResponseEntity<?> insert (@Valid @RequestBody MedicineRequest request) {
        try {
            return ResponseEntity.ok(medicineService.createMedicine(request));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update") //admin
    public ResponseEntity<?> update (@Valid @RequestBody MedicineUpdateRequest request) {
        try {
            return ResponseEntity.ok(medicineService.updateMedicine(request));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}") //admin
    public ResponseEntity<?> delete (@PathVariable Long id) {
        try{
            medicineService.deleteMedicine(id);
            return ResponseEntity.ok("success");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get") //admin
    public ResponseEntity<?> get () {
        try{
            return ResponseEntity.ok(medicineService.getAllMedicine());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
