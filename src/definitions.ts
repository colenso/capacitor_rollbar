export interface PersonData {
  id: string;
  username?: string;
  email?: string;
}

export interface CapacitorRollbarPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  setPersonData(options: { person: PersonData }): Promise<void>;
}
