package com.lti.agro.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.agro.dto.SalesDocumentDto;
import com.lti.agro.dto.SalesViewDto;
import com.lti.agro.dto.Status;
import com.lti.agro.dto.Status.StatusType;
import com.lti.agro.entity.Farmer;
import com.lti.agro.entity.Sales;
import com.lti.agro.services.SaleServices;

	@CrossOrigin
	@RestController
	public class SalesController {

		@Autowired
		SaleServices saleService;

		@GetMapping(path = "/viewMarketPlace")
		public List<SalesViewDto> viewMarketPlace() {

			return saleService.viewAllSales();

		}
		@PostMapping("/saleDocumentUploads")
		public Status uploadDocuments(SalesDocumentDto sales) {
			Status status = new Status();
			Sales  sale = saleService.findSaleById(Integer.parseInt(sales.getSalesId()));
			 String salesLocation ="D:/uploads/Sales/";
				String cropFileName1 = sales.getCropImage1().getOriginalFilename();
				String cropFile = salesLocation +cropFileName1;
				try {
					FileCopyUtils.copy(sales.getCropImage1().getInputStream(), new FileOutputStream(cropFile));
				}
				catch (IOException e) {
		            e.printStackTrace();
		            status.setStatus(StatusType.FAILURE);
		            status.setMessage(e.getMessage());
		            return status;
				}
				String cropFileName2 = sales.getCropImage2().getOriginalFilename();
				cropFile = salesLocation + cropFileName2;
				try {
					FileCopyUtils.copy(sales.getCropImage2().getInputStream(), new FileOutputStream(cropFile));
				}
				catch (IOException e) {
		            e.printStackTrace();
		            status.setStatus(StatusType.FAILURE);
		            status.setMessage(e.getMessage());
		            return status;
				}
				
			System.out.println(cropFileName1);
			System.out.println(cropFileName2);
			sale.setCropImage1(cropFileName1);
			sale.setCropImage2(cropFileName2);
			boolean result = saleService.updateSales(sale);
			if(result) {
			status.setStatus(StatusType.SUCCESS);
	        status.setMessage("Uploaded Successfully!");
			}
			else
			{
				status.setStatus(StatusType.FAILURE);
		        status.setMessage("Uploaded Failed!");
			}
	        return status;
			
		}
		
		@GetMapping(path = "/retrieveSalesDocuments")
		public Sales viewDocuments(@RequestParam("sId") int id,HttpServletRequest request)
		{
			Sales sale = saleService.findSaleById(id);
			String projPath = request.getServletContext().getRealPath("/");
			String tempDownloadPath = projPath +"/downloads/";
			File f = new File(tempDownloadPath);
			if(!f.exists())
				f.mkdir();
			
			String salesDocumentLocation="D:/uploads/Sales/";
			
			String cropFile1Target = tempDownloadPath + sale.getCropImage1();
//			System.out.println(aadhartargetFile);
			String cropFile2Target = tempDownloadPath + sale.getCropImage2();
//			System.out.println(panCardtargetFile);
			String cropFile1Source =  salesDocumentLocation + sale.getCropImage1();
//			System.out.println(aadharsourceFile);
			String cropFile2Source = salesDocumentLocation + sale.getCropImage2();
//			System.out.println(panCardsourceFile);
			try {
				FileCopyUtils.copy(new File(cropFile1Source), new File(cropFile1Target));
				FileCopyUtils.copy(new File(cropFile2Source), new File(cropFile2Target));
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			sale.setFarmer(null);
			sale.setBidder(null);
			return sale;
		}

		@GetMapping(path = "/viewSoldCropHistory")
		public List<SalesViewDto> viewSoldCropHistory(@RequestParam("fId") int fId) {

			return saleService.viewSoldCropHistory(fId);

		}
		
		@GetMapping(path = "/findSale")
		public SalesViewDto findSalesById(@RequestParam("sId") int sId)
		{
			return saleService.findSalesById(sId);
		}
		
		@GetMapping(path="/viewSellRequests")
		public List<SalesViewDto> viewSellRequest(){
			return saleService.viewSellRequest();
		}
	}



