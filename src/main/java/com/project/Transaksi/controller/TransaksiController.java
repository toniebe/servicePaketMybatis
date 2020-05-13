package com.project.Transaksi.controller;

import com.project.Transaksi.model.*;

import com.project.Transaksi.rabbitMQ.consumer.TransaksiMessageListener;
import com.project.Transaksi.rabbitMQ.producer.TransaksiMessageSender;
import com.project.Transaksi.repository.TransaksiMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaksi")
public class TransaksiController {
    @Autowired
    TransaksiMapper transaksiMapper;

    @Autowired
    TransaksiMessageSender trxSender;

    @Autowired
    TransaksiMessageListener trxListener;



    @PostMapping("/trx")
    public ResponseEntity<?> kirimTrx(@RequestBody Transaksi transaksi){
        trxSender.sendOrder(transaksi);
        TransaksiTemp transaksi1 = new TransaksiTemp();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/trx/ewallet/")
    public ResponseEntity<?> transaksiEwallet(@RequestBody User user){
        Transaksi transaksi = trxListener.getTransaksi();

        float saldo = transaksiMapper.saldo(transaksi.getUsername());
        String pass = transaksiMapper.password(transaksi.getUsername());

        if((user.getPassword().equals(pass)) && (saldo-transaksi.getHarga() > 0) ) {
            transaksiMapper.insert(transaksi);
            transaksiMapper.updateSaldo(transaksi.getHarga(),transaksi.getUsername());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("msg", "pembelian berhasil");
            return new ResponseEntity<>(jsonObject, HttpStatus.OK);
        }else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("msg","password invalid");
            return new ResponseEntity<>(jsonObject,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/trx/va/")
    public ResponseEntity<?> transaksiVA(@RequestBody Virtual va){
        Transaksi transaksi = trxListener.getTransaksi();
        String virtualAccount = transaksiMapper.virtualAccount(transaksi.getUsername());
        if(va.getVirtual().equals(virtualAccount)){
            transaksiMapper.insert(transaksi);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("msg","pembelian berhasil");
            return new ResponseEntity<>(jsonObject,HttpStatus.OK);
        }else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("msg","virtual account invalid");
            return new ResponseEntity<>(jsonObject,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/history")
    public ResponseEntity<?> getHistory(@RequestBody User user){
        List<Transaksi> transaksi= transaksiMapper.findByUsername(user.getEmail());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("history",transaksi);
        return new ResponseEntity<>(jsonObject,HttpStatus.OK);
    }


}
