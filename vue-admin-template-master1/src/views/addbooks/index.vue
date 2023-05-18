<template>
  <div class="app-container">
    <el-form ref="form" :model="bookForm" label-width="120px">
      <el-form-item label="图书ID">
        <el-input v-model="bookForm.id"/>
      </el-form-item>
      <el-form-item label="图书名称">
        <el-input v-model="bookForm.name"/>
      </el-form-item>
      <el-form-item label="作者">
        <el-input v-model="bookForm.author"/>
      </el-form-item>
      <el-form-item label="出版社">
        <el-input v-model="bookForm.press"/>
      </el-form-item>
      <el-form-item label="图书编码">
        <el-input v-model="bookForm.isbn"/>
      </el-form-item>
      <el-form-item label="图书数量">
        <el-input v-model="bookForm.numbers"/>
      </el-form-item>
      <el-select v-model="bookForm.directory.id">
        <el-option v-for="item in directoryForm"
                   :key="item.id"
                   :label="item.name"
                   :value="item.id"
        />
      </el-select>

      <el-form-item>
        <el-button type="primary" @click="onSubmit">添加</el-button>
        <el-button @click="onCancel">取消</el-button>
      </el-form-item>
    </el-form>
    <!--    <el-form ref="form" :model="form" label-width="120px">-->
    <!--      <el-form-item label="Activity name">-->
    <!--        <el-input v-model="form.name" />-->
    <!--      </el-form-item>-->
    <!--      <el-form-item label="Activity zone">-->
    <!--        <el-select v-model="form.region" placeholder="please select your zone">-->
    <!--          <el-option label="Zone one" value="shanghai" />-->
    <!--          <el-option label="Zone two" value="beijing" />-->
    <!--        </el-select>-->
    <!--      </el-form-item>-->
    <!--      <el-form-item label="Activity time">-->
    <!--        <el-col :span="11">-->
    <!--          <el-date-picker v-model="form.date1" type="date" placeholder="Pick a date" style="width: 100%;" />-->
    <!--        </el-col>-->
    <!--        <el-col :span="2" class="line">-</el-col>-->
    <!--        <el-col :span="11">-->
    <!--          <el-time-picker v-model="form.date2" type="fixed-time" placeholder="Pick a time" style="width: 100%;" />-->
    <!--        </el-col>-->
    <!--      </el-form-item>-->
    <!--      <el-form-item label="Instant delivery">-->
    <!--        <el-switch v-model="form.delivery" />-->
    <!--      </el-form-item>-->
    <!--      <el-form-item label="Activity type">-->
    <!--        <el-checkbox-group v-model="form.type">-->
    <!--          <el-checkbox label="Online activities" name="type" />-->
    <!--          <el-checkbox label="Promotion activities" name="type" />-->
    <!--          <el-checkbox label="Offline activities" name="type" />-->
    <!--          <el-checkbox label="Simple brand exposure" name="type" />-->
    <!--        </el-checkbox-group>-->
    <!--      </el-form-item>-->
    <!--      <el-form-item label="Resources">-->
    <!--        <el-radio-group v-model="form.resource">-->
    <!--          <el-radio label="Sponsor" />-->
    <!--          <el-radio label="Venue" />-->
    <!--        </el-radio-group>-->
    <!--      </el-form-item>-->
    <!--      <el-form-item label="Activity form">-->
    <!--        <el-input v-model="form.desc" type="textarea" />-->
    <!--      </el-form-item>-->
    <!--      <el-form-item>-->
    <!--        <el-button type="primary" @click="onSubmit">Create</el-button>-->
    <!--        <el-button @click="onCancel">Cancel</el-button>-->
    <!--      </el-form-item>-->
    <!--    </el-form>-->
  </div>
</template>

<script>
export default {
  data() {
    return {
      bookForm: {
        id: '',
        author: '',
        isbn: '',
        name: '',
        numbers: '',
        press: '',
        directory: {
          id:''
        }
      },
      directoryForm: {}
    }
  },
  created() {
    this.fatchDirectoryById()
  },
  methods: {
    fatchDirectoryById() {
      var vm = this
      this.axios({
          method: 'GET',
          url: 'http://localhost:8888/book/input'
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
        url: 'http://localhost:8888/book/add',
        data: vm.bookForm
      }).then(function(resp){
        vm.$router.push("/example/books")
      });
    },
    onCancel(){
      var vm = this;
      vm.$router.push("/example/books")
    }


  }
}
</script>

<style scoped>
.line {
  text-align: center;
}
</style>

