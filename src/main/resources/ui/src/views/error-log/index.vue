<template>
  <div class="errPage-container">
    <h3>
      <el-button size="mini" type="warning" @click="doIt" plain>Do It</el-button>
      {{ $t('errorLog.tips') }}
    </h3>
    <aside>
      {{ $t('errorLog.description') }}
      <!--<a target="_blank" class="link-type" href="https://panjiachen.github.io/vue-element-admin-site/guide/advanced/error.html">
        {{ $t('errorLog.documentation') }}
      </a>-->
    </aside>
    <a href="#">
      <img src="@/assets/bg_images/error-log.gif">
    </a>
  </div>
</template>

<script>
export default {
  name: 'ErrorLog',
  methods: { doIt() { return x; } }
}
</script>

<style scoped>
  .errPage-container {
    padding: 30px;
  }
</style>
