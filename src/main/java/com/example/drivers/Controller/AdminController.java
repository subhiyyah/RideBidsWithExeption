package com.example.drivers.Controller;

import com.example.drivers.ApiResponse.ApiResponse;
import com.example.drivers.Model.Admin;
import com.example.drivers.Service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    @GetMapping("/getAllAdmin")
    public ResponseEntity<?> getAllAdmin(){
        return ResponseEntity.status(200).body(new ApiResponse(""+ adminService.getAllAdmin()));
    }

    @PostMapping("/addAdmin")
    public ResponseEntity<?> addAdmin(@RequestBody @Valid Admin admin ){
        adminService.addAdmin(admin);
        return ResponseEntity.status(200).body(new ApiResponse("add admin successfully"));
    }
    @PutMapping("/updatedAdmin/{adminId}")
    public ResponseEntity<?>updateAdmin(@PathVariable Integer adminId ,@RequestBody @Valid Admin admin ){
        adminService.updateAdmin(adminId,admin);
        return ResponseEntity.status(200).body(new ApiResponse("updated done "));
    }


    @DeleteMapping("/deleteAdmin/{adminId}")

    public ResponseEntity<?>deleteAdmin(@PathVariable Integer adminId){
        adminService.deleteAdmin(adminId);
        return ResponseEntity.status(200).body(new ApiResponse("Admin deleted"));
    }


    @PutMapping("/complaint-handling/{AdminId}/{complaintId}")
    public ResponseEntity<?>complaintHandling(@PathVariable Integer AdminId, @PathVariable Integer complaintId){
        Object result=adminService.complaintHandling(AdminId,complaintId);

        return ResponseEntity.status(200).body(new ApiResponse(result.toString()));
    }

}