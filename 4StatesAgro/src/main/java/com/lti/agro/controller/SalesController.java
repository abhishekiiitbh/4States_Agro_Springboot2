package com.lti.agro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.agro.dto.SalesViewDto;
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

		@GetMapping(path = "/viewSoldCropHistory")
		public List<SalesViewDto> viewSoldCropHistory(@RequestParam("fId") int fId) {

			return saleService.viewSoldCropHistory(fId);

		}

	}



