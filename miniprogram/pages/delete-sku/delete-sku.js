// pages/delete-sku/delete-sku.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    skuid:"",
    goodname:"",
    goodid:"",
    gooddata: [],
  good:{
    "skuid":0,
    "price":0,
    "picture":"",
    "attribute":[],
  },
  goodtemp:[],
  },

  getdata:function(){
    let self = this;
    wx.request({
      url: 'http://47.105.66.104:8080/ecommerce/Shop/getSkuByGoodId',
      data: {
        GoodId: self.data.goodid,
        pageNum:1,
        pageSize:10,
      },
      method: 'GET',
      success(res) {
        console.log(res.data);
        for (var i = 0; i < res.data.data.list.length; i++) {
          self.setData({ ['good.skuid']: res.data.data.list[i].skuid });
          self.setData({ ['good.price']: res.data.data.list[i].price });
          self.setData({ ['good.picture']: res.data.data.list[i].picture });
          self.setData({ ['good.attribute']: JSON.parse(res.data.data.list[i].attribute)});
          var y = JSON.parse(JSON.stringify(self.data.good));
          var z = self.data.goodtemp;
          z.push(y);
          var a = JSON.parse(JSON.stringify(z))
          self.setData({ gooddata: a });
        }
        var b = [];
        self.setData({ goodtemp: b });
        console.log(self.data.gooddata);
        console.log(self.data.goodtemp);
        wx.setStorage({
          key: 'a1',
          data: self.data.gooddata[0].attribute[0].attrKey,
        })
        wx.setStorage({
          key: 'a2',
          data: self.data.gooddata[0].attribute[1].attrKey,
        })
      }
    })
  },

  delete:function(e){
    let self=this;
    this.setData({ skuid: e.currentTarget.dataset.skuid});
    console.log(self.data.skuid);
    wx.showModal({
      title: '提示',
      content: '是否要删除sku',
      confirmText: "确认",
      cancelText: "取消",
      success(res) {
        if (res.confirm) {
          self.deletesku();
        } else if (res.cancel) {
          console.log("取消删除")
        }
      },
    })
  },

  deletesku:function(){
    let self=this;
    wx.request({
      url: 'http://47.105.66.104:8080/ecommerce/Shop/DeleteSkuBySkuId',
      data:{
        skuId:self.data.skuid
      },
      success: function(res) {
        console.log("确认删除");
        self.getdata();
      },
    })
  },

  onClickSubmit:function(){
    wx.navigateTo({
      url: '/pages/addsku/addsku',
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let self=this;
    wx.getStorage({
      key: 'goodid',
      success: function(res) {
        self.setData({goodid:res.data});
        self.getdata();
      },
    })
    wx.getStorage({
      key: 'goodname',
      success: function(res) {
        self.setData({ goodname: res.data });
        console.log(self.data.goodname);
      },
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    let self = this;
    wx.getStorage({
      key: 'goodid',
      success: function (res) {
        self.setData({ goodid: res.data });
        self.getdata();
      },
    })
    wx.getStorage({
      key: 'goodname',
      success: function (res) {
        self.setData({ goodname: res.data });
        console.log(self.data.goodname);
      },
    })
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
    
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
    
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    
  }
})