import { cDrag } from './drag';
import { permission } from './permission';
import { cLoading } from './cLoading';
import { visibleLazy } from './visibleLazy';

//批量注册指令
const directives = {
  permission,
  cLoading,
  visibleLazy
} as any;
//注册的一般写法，循环遍历directives，通过vue.directive注册
export default {
  install(app: any) {
    Object.keys(directives).forEach(key => {
      app.directive(key, directives[key]);
    });
  }
};
