<template>
  <div class="onlinePay">
    <div class="charge-header">
      <div class="charge-header-left">
        <b class="number">{{MemberIntegral}}</b> 积分
        <p>可用积分</p>
      </div>
      <div class="charge-header-middle">
        <b class="number">{{ MemberChange }}</b> 元
        <p>可用余额</p>
      </div>
      <div class="charge-header-right">
        <b class="number">{{TotalMoney}}</b> 元
        <p>充值总额</p>
      </div>
    </div>
    <div class="charge-recharge">
      <div class="charge-recharge-middle">
        <el-button size="small" v-for="button in buttons" @click="yuan(button)">{{button}}元</el-button>
       <div class="charge-recharge-middle-button">
          <el-button type="primary" style="width: 450px" @click="pay">立即充值</el-button>
        </div>
      </div>
      <div class="payIcon">
        <a href="javascript:;"><img src="../../assets/images/微信支付.png" alt=""></a>
        <a href="javascript:;"><img src="../../assets/images/支付-支付宝.png" alt=""></a>
        <a href="javascript:;"><img src="../../assets/images/银联.png" alt=""></a>
      </div>
    </div>

  </div>

</template>

<script>
import {
  addEmployee,
  addRechargeByMemberNo,
  getMemberChange,
  getMemberIntegral,
  getRechargeByMemberNo,
  getTotalMoney, updateMemberChange
} from "@/api/allApi";

export default {
  name: "OnlinePay",
  data() {
    return{
      input:'',
      buttons:['10','30','50','100','200'],
      admin:{

      },
      MemberIntegral:'',
      MemberChange:'',
      TotalMoney:'',
      price:'',
    }
  },
  created() {
    this.admin = JSON.parse(window.localStorage.getItem('access-member'))
  },
  mounted() {
    this.getMemberIntegral()
    this.getTotalMoney()
    this.getMemberChange()
  },
  methods:{
    yuan(button){
      this.price = button
    },
    inputClick() {
      this.price = this.input
    },
    //立即支付
    pay(){

      let _this = this
      if(_this.price > 0){
        addRechargeByMemberNo({
          memberNo: _this.admin.memberNo,
          rechargeMoney:this.price,
        }).then(res=>{
          if(res.data.code===200){
            alert(res.data.message)
            updateMemberChange({
              memberNo:_this.admin.memberNo
            })
            this.getMemberChange()
            this.getTotalMoney()
          }else{
            alert(res.data.message)
          }
        }).catch(err=>{
          console.log(err)
        })
      }
      else{
        alert("充值成功")
        this.getMemberChange()
        this.getTotalMoney()
      }

    },
    getMemberIntegral(){
      let _this = this
      getMemberIntegral({
        memberNo: _this.admin.memberNo}).then(res=>{
        this.MemberIntegral = res.data
      }).catch(err=>{
        console.log(err.message)
      })
    },

    getTotalMoney(){
      let _this = this
      getTotalMoney({
        memberNo: _this.admin.memberNo}).then(res=>{
        this.TotalMoney = res.data
      }).catch(err=>{
        console.log(err.message)
      })
    },
    getMemberChange(){
      let _this = this
      getMemberChange({
        memberNo: _this.admin.memberNo}).then(res=>{
        this.MemberChange = res.data
      }).catch(err=>{
        console.log(err.message)
      })
    },
  }
}
</script>

<style scoped>
.onlinePay {
  padding: 40px;
}

.charge-header {
  background-color: #c2a7a7;

}

.charge-header-left ,
.charge-header-middle,
.charge-header-right
{
  float: left;
  width: 293px;
  height: 125px;
  margin-right: 20px;
  border: 1px solid #00c996;
  border-radius: 5px;
  padding: 20px;
}

.charge-header-left p,
.charge-header-middle p,
.charge-header-right p{
  margin-top: 15px;
  font-size: 14px;
  color: #8a92a9;

}

.charge-header-right {
  margin-right: 0px;
}

.charge-recharge {
  float: left;
  width: 600px;
  height: 43px;
  margin: 60px 160px;
  text-align: center;

}

.charge-recharge-middle {
  margin: auto;
}

.charge-recharge-middle-button {
  width: 600px;
  padding-top: 60px;
}


.number {
  font-size: 32px;
}

.payIcon {
  text-align: center;
  width:600px;
  height: 45px;
  margin-top: 30px;
}

.payIcon a{
  width: 45px;
  height: 45px;
  margin-right: 10px;
}

.payIcon a img{
  width: 45px;
  height: 45px;
}

</style>
