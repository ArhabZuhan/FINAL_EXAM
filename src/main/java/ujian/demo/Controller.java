/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ujian.demo;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ujian.demo.exceptions.NonexistentEntityException;

/**
 *
 * @author Arhab Zuhan Assaifuddin 20200140062
 */

@RestController
@CrossOrigin
@RequestMapping("/Sim")
public class Controller {
    Sim sim = new Sim();
    SimJpaController ctrl = new SimJpaController();
    
    List<Sim> simList = new ArrayList<Sim>();
    

    @GetMapping()
    public List<Sim> viewAll() {
        try {
            return ctrl.findSimEntities();
        } catch (Exception e) {
            return List.of();
        }
    }
    @PostMapping()
    public String postMember1(@RequestBody Sim sim )
    {
        try {
            ctrl.create(sim);
            return "Data Tersimpan";
        } catch (Exception e) {
            return "Gagal Nyimpen";
        }
    }
    
    @PutMapping()
    public String editMember1(@RequestBody Sim sim )
    {
        try {
            ctrl.edit(sim);
            return "Data Terbarui";
        } catch (Exception e) {
            return "Update Gagal";
        }
    }
    
    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") int id){
        try{
             ctrl.destroy(id);
              return "DATA NOMOR " + id + " TERHAPUS";
        }
        catch(NonexistentEntityException error){
            return "GAGAL HAPUS";
        }
    }
    
}
    
