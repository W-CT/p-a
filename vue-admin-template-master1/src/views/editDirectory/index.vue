<template>
  <div class="app-container">
    <el-form ref="form" :model="directoryForm" label-width="120px">
      <el-form-item label="类别名称">
        <el-input v-model="directoryForm.name"/>
      </el-form-item>
      <el-form-item label="类别描述">
        <el-input v-model="directoryForm.describes"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">编辑</el-button>
        <el-button @click="onCancel">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      directoryForm: {
        id:'',
        name:'',
        describes:''
      }
    }
  },
  created() {
    this.fatchDataById()
  },
  methods: {
    fatchDataById() {
      var vm = this
      var bid = this.$route.params.id
      this.axios({
          method: 'GET',
          url: 'http://localhost:8888/directory/getDirectoryById?id=' + bid
        }
      ).then(function(resp) {
        console.log(resp)
        vm.directoryForm = resp.data
      })
    },
    onSubmit(){
      var vm = this;
      this.axios({
        method: 'POST',
        url: 'http://localhost:8888/directory/update',
        data: vm.directoryForm
      }).then(function(resp){
        vm.$router.push("/example/directorys")
      });
    },
    onCancel(){
      var vm = this;
      vm.$router.push("/example/directorys")
    }


  }
}
</script>

<style scoped>
.line {
  text-align: center;
}
</style>

