<template>
  <div class="tinymce-container editor-container" :class="{fullscreen:fullscreen}">
    <textarea :id="tinymceId" class="tinymce-textarea" />
    <!-- <div class="editor-custom-btn-container">
      <multiple-upload class="editor-upload-btn" @success="imageSuccess" />
    </div> -->
  </div>
</template>

<script>
import axios from 'axios'
import editorImage from './components/editorImage'
import plugins from './plugins'
import toolbar from './toolbar' 

export default {
  name: 'Tinymce',
  components: {
    editorImage
  },
  props: {
    // 默认填充到富文本的html文件
    html: {
      type: String,
      default: ''
    },
    toolbar: {
      type: Array,
      default() {
        return []
      }
    },
    menubar: {
      type: Boolean,
      default: false
    },
    height: {
      type: Number,
      default: 400
    }
  },
  data() {
    return {
      hasChange: false,
      hasInit: false,
      tinymceId: 'vue-tinymce-' + +new Date(),
      fullscreen: false,
      value: '',
      editorContent: ''
    }
  },
  watch: {
    value(val) {
      this.$nextTick(() =>
        window.tinymce.get(this.tinymceId).setContent(val || '')
      )
    },
    html(val) {
      if (this.isUrl) {
        this.loadUrl(val)
      }
    }
  },
  created() {
    if (this.html && this.html.startsWith('http')) {
      this.loadUrl(this.html)
    } else {
      this.value = this.html + ''
      this.editorContent = this.html + ''
    }
  },
  mounted() {
    this.initTinymce()
  },
  activated() {
    this.initTinymce()
  },
  deactivated() {
    this.destroyTinymce()
  },
  destroyed() {
    this.destroyTinymce()
  },
  methods: {
    initTinymce() {
      window.tinymce.init({
        fontsize_formats: '12px 14px 16px 18px 20px 24px 36px',
        language: 'zh_CN',
        language_url: '../../../public/tinymce_5.2.0/tinymce/js/tinymce/langs/zh_CN.js',
        selector: `#${this.tinymceId}`,
        height: this.height,
        body_class: 'panel-body ',
        object_resizing: true,
        toolbar: this.toolbar.length > 0 ? this.toolbar : toolbar,
        menubar: this.menubar,
        plugins: plugins,
        end_container_on_empty_block: true,
        powerpaste_word_import: 'clean',
        code_dialog_height: 450,
        code_dialog_width: 1000,
        advlist_bullet_styles: 'square',
        advlist_number_styles: 'default',
        default_link_target: '_blank',
        link_title: false,
        init_instance_callback: editor => {
          if (this.value) {
            editor.setContent(this.value)
          }
          this.hasInit = true
          editor.on('NodeChange Change KeyUp SetContent', () => {
            this.hasChange = true
            this.$emit('input', editor.getContent())
            this.editorContent = editor.getContent()
          })
        },
        setup(editor) {
          editor.on('FullscreenStateChanged', e => {
            this.fullscreen = e.state
          })
        }
      })
    },
    destroyTinymce() {
      if (window.tinymce.get(this.tinymceId)) {
        window.tinymce.get(this.tinymceId).destroy()
      }
    },
    loadUrl(url) {
      if (url && url.length > 0) {
        axios
          .get(url)
          .then(response => {
            // 处理HTML显示
            this.value = response.data
            this.editorContent = response.data
            this.$emit('subLoadUrlToHtml', response.data)
            this.$emit('input', response.data)
          })
          .catch(() => {
            this.value = '服务器数据加载失败，请重试!'
          })
      }
    },
    // 设置编辑器内容
    setContent(value) {
      window.tinymce.get(this.tinymceId).setContent(value)
    },
    // 获取编辑器内容
    getContent() {
      window.tinymce.get(this.tinymceId).getContent()
    },
    // 图片上传成功后填充到富文本编辑器
    /*async imageSuccess(urlList) {
      try {
        let imageTemplateList = ''
        urlList.forEach(item => {
          const image = `<img style="max-width:100%;" src="${item}">`
          imageTemplateList = imageTemplateList + image
        })
        window.tinymce.get(this.tinymceId).insertContent(imageTemplateList)
        this.$message({
          message: '上传成功！',
          type: 'success'
        })
      } catch (error) {
        console.log(error)
        this.$message({
          message: error,
          type: 'error'
        })
      }
    },*/
    // 编辑器内容上传到cos，调用返回url
    async content2Url() {
      try {
        const res = await uploadHtml(this.editorContent)
        return res
      } catch (error) {
        this.$message({
          message: error.data.message,
          type: 'error'
        })
      }
    }
  }
}
</script>

<style lang='scss' >
#tinymce {
  background-color: blue;
  p {
    margin: 0;
  }
}
.tinymce-container {
  position: relative;
}
.tinymce-container >>> .mce-fullscreen {
  z-index: 10000;
}
.tinymce-textarea {
  visibility: hidden;
  z-index: -1;
}
.editor-custom-btn-container {
  position: absolute;
  right: 4px;
  top: 4px;
  /*z-index: 2005;*/
}
.fullscreen .editor-custom-btn-container {
  z-index: 10000;
  position: fixed;
}
.editor-upload-btn {
  display: inline-block;
}
// 隐藏底部logo栏
.mce-edit-area + .mce-statusbar {
  opacity: 0;
  height: 0;
}
</style>