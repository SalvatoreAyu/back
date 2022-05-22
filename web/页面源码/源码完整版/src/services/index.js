import HTTP from '../libs/http';
import { setPageData } from '../libs/util';

class Service extends HTTP {
  getNewsList(type, count) {
    return new Promise((resolve, reject) => {
      this.ajax({
        url: 'http://api.salvatore.ink/toutiao/index',
        type: 'POST',
        data: {
          key: '33555ccf0c09287bb83b80f74672f153',
          type: type,
        },
        success(data) {
          if (data.resultcode == 112) {
            console.log(data);
            data = 'noMoney';
            resolve(data);
          } else {
            const pageData = setPageData(data.result.data, count);
            resolve(pageData);
          }
        },
        error(err) {
          reject(err);
        },
      });
    });
  }
}

export default new Service();
