/*
 * @Description:字典数据
 */

import { defineStore } from 'pinia';
import { ref } from 'vue';

export const useDictStore = defineStore('dict', {
  state: () => ({
    dict: new Array()
  }),
  actions: {
    // 获取字典
    getDict(key: any) {
      if (key == null && key == '') {
        return null;
      }
      try {
        for (let i = 0; i < this.dict.length; i++) {
          if (this.dict[i].key == key) {
            return this.dict[i].value;
          }
        }
      } catch (e) {
        return null;
      }
    },
    // 设置字典
    setDict(key: any, value: any) {
      if (key !== null && key !== '') {
        this.dict.push({
          key: key,
          value: value
        });
      }
    },
    // 删除字典
    removeDict(key: any) {
      var bln = false;
      try {
        for (let i = 0; i < this.dict.length; i++) {
          if (this.dict[i].key == key) {
            this.dict.splice(i, 1);
            return true;
          }
        }
      } catch (e) {
        bln = false;
      }
      return bln;
    },
    // 清空字典
    cleanDict() {
      this.dict = new Array();
    }
  }
});
