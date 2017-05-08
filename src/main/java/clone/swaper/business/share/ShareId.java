package clone.swaper.business.share;


import clone.swaper.infrastructure.persistence.Id;

import java.util.UUID;

public class ShareId extends Id {
    public ShareId(UUID uuid) {
        super(uuid);
    }
    
    public ShareId(Id id) {
        super(id);
    }
}
