import { WebPlugin } from '@capacitor/core';

import type { CapacitorRollbarPlugin } from './definitions';

export class CapacitorRollbarWeb
  extends WebPlugin
  implements CapacitorRollbarPlugin
{
  setPersonData(): Promise<void> {
    throw new Error('Method not implemented.');
  }
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
