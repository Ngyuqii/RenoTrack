import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { AppModule } from './app/app.module';
import { registerLicense } from '@syncfusion/ej2-base';

registerLicense('ORg4AjUWIQA/Gnt2VFhiQlJPcEBAWXxLflF1VWRTe116d1FWACFaRnZdQV1mS3tSf0FnW39ad3dd');

platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => console.error(err));
