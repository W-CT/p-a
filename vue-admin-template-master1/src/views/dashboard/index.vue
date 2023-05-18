<template>
  <div class="dashboard-container">
    <div class="block">
      <el-timeline>
        <el-timeline-item
          v-for="(activity, index) in activities"
          :key="index"
          :icon="activity.icon"
          :color="activity.color"
          :timestamp="activity.timestamp">
          {{activity.content}}
        </el-timeline-item>
      </el-timeline>
    </div>
  </div>

</template>

<script>
import { mapGetters } from 'vuex'
import Timeline from '@/components/timeline'
import TimelineItem from '@/components/timeline-item'
export default {
  components: { Timeline,TimelineItem },
  data() {
    return {
      activities: [],
      count:5,
      list:[]
    }
  },
  created() {
    this.fetchData()
  },
  methods:{
    fetchData() {
      var vm = this
      this.axios({
        method: 'GET',
        url: 'http://localhost:8888/record/input'
      }).then(function(resp) {
        console.log(resp.data)
        vm.activities = resp.data
      })
    },
  },
  load(){
    var vm = this
    vm.count +=1
  },
  name: '图书管理系统',
  computed: {
    ...mapGetters([
      'name'
    ])
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
