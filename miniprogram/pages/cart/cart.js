// pages/test/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    address: {},
    goodData: [],
    allChecked: false,
    totalprice: 0,
    totalnumber: 0,
    token:null,
  },
  
  getgoodData () {
    //显示加载
    wx.showLoading({
      title: '加载中',
    });
    wx.request({
      url: 'http://47.105.66.104:8080/ecommerce/User/GetAllChartByUserId', //仅为示例，并非真实的接口地址
      data: {
        pageNum: 1,
        pageSize:11,  
      },
      method: 'GET',
      header: {
        'Authorization': 'Bearer '+this.data.token
      },
      success:(res)=> {
        wx.hideLoading();
        console.log(res)
        res.data.data.list.forEach(v => {
          var tempatt=JSON.parse(v.attribute);
          v.att1=tempatt[0].attrValue;
          v.att2=tempatt[1].attrValue;
        })
        this.setData({goodData:res.data.data.list});
        this.setCart(this.data.goodData);
      }
      
    }
    )
  },
  jumptogood: function (e) {
    console.log(e);
    //点击后要获取商品的数据（一般是ID）
    //在进行跳转，将goodid给商品详情页
    let goodno = e.currentTarget.dataset.goodid;
    console.log(goodno)
    wx.navigateTo({
      url: '/pages/good-detail/good-detail?goodno=' + goodno,
    })
  },
  onShow:function() {
    wx.getStorage({
      key: 'token',
      success:(res)=>{
        this.setData({token:res.data});
        console.log(this.data.token);
        this.getgoodData();
      }
    })
  },
  onLoad:function(){
    wx.getStorage({
      key: 'token',
      success:(res)=>{
        this.setData({token:res.data});
        console.log(this.data.token);
        this.getgoodData();
      }
    })
  },
  
  //商品选中
  handleItemChange: function (e) {
    //获取被修改的商品id
    const goodsno = e.currentTarget.dataset.id;
    //获取购物车数组
    let gooddata = this.data.goodData;
    //找到被修改的商品对象
    let index = gooddata.findIndex(v => v.chartid === goodsno);
    //选中状态取反
    gooddata[index].checkstate = !gooddata[index].checkstate;
    //设置数据回data
    this.setCart(gooddata);
    ////////////////////////////////////////////////////////////这里要有写回去后台的操作！
  },

  //商品全选功能
  handleItemAllcheck: function () {
    let gooddata = this.data.goodData;
    let allchecked = this.data.allChecked;
    //反选
    allchecked = !allchecked;
    gooddata.forEach(v => v.checkstate = allchecked);
    //保存
    this.setCart(gooddata);
  },

  //商品数量编辑+-
  handleItemNumEdit: function (e) {
    const oper = e.currentTarget.dataset.operation;
    const id = e.currentTarget.dataset.id;
    let gooddata = this.data.goodData;
    const index = gooddata.findIndex(v => v.chartid === id);
    //判断是否删除
    if (gooddata[index].number === 1 && oper === -1) {
      //弹窗
      wx.showModal({
        content: '您是否要删除商品？',
        success: (result) => {
          if (result.confirm) {
            wx.request({
              url: 'http://47.105.66.104:8080/ecommerce/User/DeleteChartByGoodId',
              data: {
                  chartId:gooddata[index].chartid,
              },
              method: 'GET',
              header: {
                'Authorization': 'Bearer '+this.data.token
              },
              success:(res)=> {
                gooddata.splice(index, 1);
                this.setCart(gooddata);
              }
            })
          }
        },
        title: '提示',
      })
    } else {
      gooddata[index].number += oper;
      wx.request({
        url: 'http://47.105.66.104:8080/ecommerce/User/UpdateNumInChart',
        data: {
            chartId:gooddata[index].chartid,
            GoodId:gooddata[index].goodid,
            Attribute:gooddata[index].attribute,
            num:gooddata[index].number,
        },
        method: 'GET',
        header: {
          'Authorization': 'Bearer '+this.data.token
        },
        success:(res)=> {
          this.setCart(gooddata);
        }
      })
    }
  },
  handlePay: function () {
    //验证收货地址？
    //判断有没有选购商品
    const total = this.data.totalnumber;
    if (total === 0) {
      wx.showToast({
        title: '您还没有选购商品',
        icon: "none",
      });
      return;
    } else {//跳转到支付，或者是直接数据库操作？预计跳转到一个界面展示是否成功？
      wx.navigateTo({
        url: '../pay/pay',
      })
    }
  },

  //设置购物车状态同时重新计算底部工具栏数据
  setCart(gooddata) {
    var i = 0;
    let totalprice = 0;
    let totalnumber = 0;
    this.data.goodData.forEach(v => {
      if (v.checkstate) {
        totalprice += v.number * v.price;
        totalnumber += v.number;
        ++i;
      } else {
        this.setData({ allChecked: false });
      }
    })
    if (i === this.data.goodData.length) {
      this.setData({ allChecked: true });
    }
    if (this.data.goodData.length === 0) {
      this.setData({ allChecked: false });
    }
    this.setData({ totalprice: totalprice });
    this.setData({ totalnumber: totalnumber });
    this.setData({ goodData: gooddata });
    wx.setStorageSync('goodData', gooddata);
  },
  

})