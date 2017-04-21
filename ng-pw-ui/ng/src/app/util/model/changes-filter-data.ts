import { SelectItem } from 'primeng/primeng';

export interface ChangesFilterData {
  projects?: string[];
  businessRuleTables?: SelectItem[];
  users?: string[];
}
