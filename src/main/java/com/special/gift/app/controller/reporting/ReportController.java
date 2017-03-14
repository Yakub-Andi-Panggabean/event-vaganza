package com.special.gift.app.controller.reporting;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.special.gift.app.domain.Invoice;

@Controller
@RequestMapping(value = ReportController.BASE_PATH)
public class ReportController {

  public static final String BASE_PATH = "/report";

  private final String INVOICE_PATH = "/invoice";

  @RequestMapping(value = INVOICE_PATH, method = RequestMethod.GET)
  public ModelAndView modelAndView(ModelAndView view) {
    final Iterable<Invoice> list = new ArrayList<>();
    view.addObject("reportDate", new Date());
    view.addObject("dataSource", list);
    view.addObject("format", "pdf");
    view.setViewName("invoice");
    return view;
  }


}
