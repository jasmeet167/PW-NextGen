
import { SelectItem } from 'primeng/primeng';

export interface AuditFilterData {
  packages?: SelectItem[];
  projects?: string[];
  businessRuleTables?: SelectItem[];
  users?: string[];
}
