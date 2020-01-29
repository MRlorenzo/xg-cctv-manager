<template>
  <div class="dashboard-container">
    <component :is="currentRole" />
  </div>
</template>

<script>
import editorDashboard from './editor'

export default {
  name: 'Dashboard',
  components: { editorDashboard },
  data() {
    return {
      currentRole: 'editorDashboard'
    }
  },
  created() {

  }
}
</script>
