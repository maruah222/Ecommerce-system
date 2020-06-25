// pages/good-detail/good-detail.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    firstIndex: -1,
    //准备数据 
    //数据结构：以一组一组来进行设定 
    commodityAttr: [
      {
        priceId: 1,
        price: 35.0,
        "stock": 8,
        "Attribute": [
          {
            "attrKey": "颜色",
            "attrValue": "白色"
          },
          {
            "attrKey": "内存",
            "attrValue": "128G"
          },
        ]
      },
    ],
    Attribute: [],
    attribute1: [],
    title:"",
    indicatorDots: false,
    vertical: false,
    autoplay: true,
    interval: 2000,
    duration: 500,
    current: 0,
    goodsNo: "",/*用来接收首页传来的goodno*/
    token: "",/*用来传输文件头*/
    number: 1,
    attributetemp: "",
    Address: "这是一个商品地址",
    price: 0,
    "SwiperImgList":[
    ],
    "DetailImgList": [
    ],
    sku: {
      "skuid": 0,
      "goodid": "",
      "number": 0,
      "price": 0,
      "vipprice": 0,
      "leftNumber": 0,
      "picture": "0",
      "Attribute": []
    },
    skuss: [],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    /*console.log(options)*/
    this.data.goodsNo = options.goodno;
    /*console.log(this.data.goodsNo);*/
    this.getdata();
    let self = this;
    wx.getStorage({
      key: 'token',
      success: function (res) {
        self.setData({ token: res.data })
      }
    });
  },

  //获取数据接口
  getdata: function () {
    let self = this;
    wx.request({
      url: 'http://47.105.66.104:8080/ecommerce/User/GetGoodsDetailsByGoodId',
      data:{
        GoodId: self.data.goodsNo,
      },
      method: 'GET',
      success(res) {
        for (var i = 0; i < res.data.data.skus.length; i++) {
          self.setData({ ['sku.skuid']: res.data.data.skus[i].skuid });
          self.setData({ ['sku.goodid']: res.data.data.skus[i].goodid });
          self.setData({ ['sku.number']: res.data.data.skus[i].number });
          self.setData({ ['sku.price']: res.data.data.skus[i].price });
          self.setData({ ['sku.vipprice']: res.data.data.skus[i].vipprice });
          self.setData({ ['sku.leftNumber']: res.data.data.skus[i].leftNumber });
          self.setData({ ['sku.picture']: res.data.data.skus[i].picture });
          self.setData({ ['sku.Attribute']: JSON.parse(res.data.data.skus[i].attribute) });
          var y = JSON.parse(JSON.stringify(self.data.sku));
          var z = self.data.skuss;
          z.push(y);
          /*console.log(self.data.skuss);*/
          self.setData({ skuss: z });
        }
        self.setData({commodityAttr:self.data.skuss})
        /*console.log(self.data.commodityAttr);*/
        self.setData({
          includeGroup: self.data.commodityAttr
        });
        self.distachAttrValue(self.data.commodityAttr);
        // 只有一个属性组合的时候默认选中 
        // console.log(this.data.Attribute); 
        if (self.data.commodityAttr.length == 1) {
          for (var i = 0; i < self.data.commodityAttr[0].Attribute.length; i++) {
            this.data.Attribute[i].selectedValue = self.data.commodityAttr[0].Attribute[i].attrValue;
          }
          self.setData({
            Attribute: self.data.Attribute
          });
          console.log(self.data.Attribute);
        }
        var a = res.data.data.goodpicture;
        var b = res.data.data.skus[0].picture;
        var x = res.data.data.frontpicture;
        var y = self.data.SwiperImgList;
        y.push(x);
        y.push(a);
        y.push(b);
        self.setData({ SwiperImgList: y });
        self.setData({ DetailImgList:y  });
        self.setData({Address:res.data.data.shopAddress});
        self.setData({title:res.data.data.goodname})
      }
    })
  },

  //切换事件,设置当前切换时候的底部坐标
  swiperchange: function (e) {
    let currentnum = e.detail.current;
    this.setData({ current: currentnum })
  },

  //跳转回首页
  jumptohome: function () {
    wx.switchTab({
      url: '/pages/base/base',
    })//跳转到tabbar页面并关闭其他页面
  },
  //出现选择界面,目前不会实现
  choose: function () {
    console.log("我不会")
  },
  //跳到购物车界面
  jumptocart: function () {
    wx.switchTab({
      url: '/pages/cart/cart',
    })
  },

  //加入购物车
  addtocart: function () {
    let self = this;
    wx.request({
      url: 'http://47.105.66.104:8080/ecommerce/User/AddChart',
      header: {
        'Authorization': 'Bearer ' + self.data.token
      },
      data: {
        GoodId: this.data.goodsNo,
        number: this.data.number,
        price: this.data.price,
        Attribute: this.data.attributetemp
      },
      success: function (res) {
        console.log(res);
      },
      fail: function (res) {
        console.log(加入失败)
      },
      complete: function (res) { },
    }),
      wx.showToast({
        title: '加入成功',
        icon: 'success',
      });
  },



  onShow: function () {
   /* this.setData({
      includeGroup: this.data.commodityAttr
    });
    this.distachAttrValue(this.data.commodityAttr);
    // 只有一个属性组合的时候默认选中 
    // console.log(this.data.Attribute); 
    if (this.data.commodityAttr.length == 1) {
      for (var i = 0; i < this.data.commodityAttr[0].Attribute.length; i++) {
        this.data.Attribute[i].selectedValue = this.data.commodityAttr[0].Attribute[i].attrValue;
      }
      this.setData({
        Attribute: this.data.Attribute
      });
    }*/
  },
  /* 获取数据 */
  distachAttrValue: function (commodityAttr) {
    /** 
    将后台返回的数据组合成类似 
    { 
    attrKey:'型号', 
    Attribute:['1','2','3'] 
    } 
    */
    // 把数据对象的数据（视图使用），写到局部内 
    var Attribute = this.data.Attribute;
    // 遍历获取的数据 
    for (var i = 0; i < commodityAttr.length; i++) {
      for (var j = 0; j < commodityAttr[i].Attribute.length; j++) {
        var attrIndex = this.getAttrIndex(commodityAttr[i].Attribute[j].attrKey, Attribute);
        // console.log('属性索引', attrIndex); 
        // 如果还没有属性索引为-1，此时新增属性并设置属性值数组的第一个值；索引大于等于0，表示已存在的属性名的位置 
        if (attrIndex >= 0) {
          // 如果属性值数组中没有该值，push新值；否则不处理 
          if (!this.isValueExist(commodityAttr[i].Attribute[j].attrValue, Attribute[attrIndex].attrValues)) {
            Attribute[attrIndex].attrValues.push(commodityAttr[i].Attribute[j].attrValue);
          }
        } else {
          Attribute.push({
            attrKey: commodityAttr[i].Attribute[j].attrKey,
            attrValues: [commodityAttr[i].Attribute[j].attrValue]
          });
        }
      }
    }
    // console.log('result', Attribute) 
    for (var i = 0; i < Attribute.length; i++) {
      for (var j = 0; j < Attribute[i].attrValues.length; j++) {
        if (Attribute[i].attrValueStatus) {
          Attribute[i].attrValueStatus[j] = true;
        } else {
          Attribute[i].attrValueStatus = [];
          Attribute[i].attrValueStatus[j] = true;
        }
      }
    }
    this.setData({
      Attribute: Attribute
    });
  },
  getAttrIndex: function (attrName, Attribute) {
    // 判断数组中的attrKey是否有该属性值 
    for (var i = 0; i < Attribute.length; i++) {
      if (attrName == Attribute[i].attrKey) {
        break;
      }
    }
    return i < Attribute.length ? i : -1;
  },
  isValueExist: function (value, valueArr) {
    // 判断是否已有属性值 
    for (var i = 0; i < valueArr.length; i++) {
      if (valueArr[i] == value) {
        break;
      }
    }
    return i < valueArr.length;
  },
  /* 选择属性值事件 */
  selectAttrValue: function (e) {
    /*
    点选属性值，联动判断其他属性值是否可选 
    { 
    attrKey:'型号', 
    Attribute:['1','2','3'], 
    selectedValue:'1', 
    attrValueStatus:[true,true,true] 
    } */
    //console.log(e.currentTarget.dataset); 
    //console.log(e);

    var Attribute = this.data.Attribute;
    var index = e.currentTarget.dataset.index;//属性索引 
    var key = e.currentTarget.dataset.key;
    var value = e.currentTarget.dataset.value;
    if (e.currentTarget.dataset.status || index == this.data.firstIndex) {
      if (e.currentTarget.dataset.selectedvalue == e.currentTarget.dataset.value) {
        // 取消选中 
        this.disSelectValue(Attribute, index, key, value);
      } else {
        // 选中 
        this.selectValue(Attribute, index, key, value);
      }

    }
  },
  /* 选中 */
  selectValue: function (Attribute, index, key, value, unselectStatus) {
    //console.log('firstIndex', this.data.firstIndex); 
    var includeGroup = [];
    if (index == this.data.firstIndex && !unselectStatus) { // 如果是第一个选中的属性值，则该属性所有值可选 
      var commodityAttr = this.data.commodityAttr;
      // 其他选中的属性值全都置空 
      // console.log('其他选中的属性值全都置空', index, this.data.firstIndex, !unselectStatus); 
      for (var i = 0; i < Attribute.length; i++) {
        for (var j = 0; j < Attribute[i].attrValues.length; j++) {
          Attribute[i].selectedValue = '';
        }
      }
    } else {
      var commodityAttr = this.data.includeGroup;
    }

    // console.log('选中', commodityAttr, index, key, value); 
    for (var i = 0; i < commodityAttr.length; i++) {
      for (var j = 0; j < commodityAttr[i].Attribute.length; j++) {
        if (commodityAttr[i].Attribute[j].attrKey == key && commodityAttr[i].Attribute[j].attrValue == value) {
          includeGroup.push(commodityAttr[i]);
        }
      }
    }
    Attribute[index].selectedValue = value;

    // 判断属性是否可选 
    for (var i = 0; i < Attribute.length; i++) {
      for (var j = 0; j < Attribute[i].attrValues.length; j++) {
        Attribute[i].attrValueStatus[j] = false;
      }
    }
    for (var k = 0; k < Attribute.length; k++) {
      for (var i = 0; i < includeGroup.length; i++) {
        for (var j = 0; j < includeGroup[i].Attribute.length; j++) {
          if (Attribute[k].attrKey == includeGroup[i].Attribute[j].attrKey) {
            for (var m = 0; m < Attribute[k].attrValues.length; m++) {
              if (Attribute[k].attrValues[m] == includeGroup[i].Attribute[j].attrValue) {
                Attribute[k].attrValueStatus[m] = true;
              }
            }
          }
        }
      }
    }
    //console.log('结果', Attribute); 
    this.setData({
      Attribute: Attribute,
      includeGroup: includeGroup
    });

    var count = 0;
    for (var i = 0; i < Attribute.length; i++) {
      for (var j = 0; j < Attribute[i].attrValues.length; j++) {
        if (Attribute[i].selectedValue) {
          count++;
          break;
        }
      }
    }
    if (count < 2) {// 第一次选中，同属性的值都可选 
      this.setData({
        firstIndex: index
      });
    } else {
      this.setData({
        firstIndex: -1
      });
    }
  },
  /* 取消选中 */
  disSelectValue: function (Attribute, index, key, value) {
    var commodityAttr = this.data.commodityAttr;
    Attribute[index].selectedValue = '';

    // 判断属性是否可选 
    for (var i = 0; i < Attribute.length; i++) {
      for (var j = 0; j < Attribute[i].attrValues.length; j++) {
        Attribute[i].attrValueStatus[j] = true;
      }
    }
    this.setData({
      includeGroup: commodityAttr,
      Attribute: Attribute
    });

    for (var i = 0; i < Attribute.length; i++) {
      if (Attribute[i].selectedValue) {
        this.selectValue(Attribute, i, Attribute[i].attrKey, Attribute[i].selectedValue, true);
      }
    }
  },
  /* 点击确定 */
  choose: function () {
    var value = [];
    for (var i = 0; i < this.data.Attribute.length; i++) {
      if (!this.data.Attribute[i].selectedValue) {
        break;
      }
      value.push(this.data.Attribute[i].selectedValue);
    }
    if (i < this.data.Attribute.length) {
      wx.showToast({
        title: '请完善属性',
        icon: 'loading',
        duration: 1000
      })
    }
    else {
      console.log(value);
      for (var i = 0; i < this.data.commodityAttr.length; i++) {
        if (this.data.commodityAttr[i].Attribute[0].attrValue == value[0] && this.data.commodityAttr[i].Attribute[1].attrValue == value[1]) {
          this.setData({ attribute1: this.data.commodityAttr[i].Attribute })
          console.log(this.data.attribute1);
          var citystr = JSON.stringify(this.data.attribute1);
          this.setData({ attributetemp: citystr });
          console.log(this.data.attributetemp)
          this.setData({ price: this.data.commodityAttr[i].price })
          console.log(this.data.commodityAttr[i].price);
          break;
        }
      }
      wx.showToast({
        title: '选择成功',
        icon: 'sucess',
        duration: 1000
      })
    }
  },
})