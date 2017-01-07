package com.special.gift.app.util.response;

public class PageInformation {

  private Integer page;
  private Integer pageSize;
  private Integer totalPage;
  private Long totalRecord;

  public PageInformation() {
    super();
    // TODO Auto-generated constructor stub
  }

  public PageInformation(Integer page, Integer pageSize, Long totalRecord) {
    super();
    final Double tPage = Math.ceil((double) totalRecord / pageSize);
    if (page > tPage) {
      this.page = tPage.intValue();
    } else {
      this.page = page;
    }
    this.pageSize = pageSize;
    totalPage = tPage.intValue();
    this.totalRecord = totalRecord;
  }

  public Integer getpage() {

    return page;
  }

  public void setpage(Integer page) {
    this.page = page;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public Long getTotalRecord() {
    return totalRecord;
  }

  public void setTotalRecord(Long totalRecord) {
    this.totalRecord = totalRecord;
  }

  public Integer getTotalPage() {
    return totalPage;
  }

  public void setTotalPage(Integer totalPage) {
    this.totalPage = totalPage;
  }

  @Override
  public String toString() {
    return "PageInformation [page=" + page + ", pageSize=" + pageSize + ", totalPage=" + totalPage
        + ", totalRecord=" + totalRecord + "]";
  }

}
