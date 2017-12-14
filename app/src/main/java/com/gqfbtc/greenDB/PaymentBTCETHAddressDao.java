package com.gqfbtc.greenDB;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.gqfbtc.entity.bean.PaymentBTCETHAddress;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PAYMENT_BTCETHADDRESS".
*/
public class PaymentBTCETHAddressDao extends AbstractDao<PaymentBTCETHAddress, Long> {

    public static final String TABLENAME = "PAYMENT_BTCETHADDRESS";

    /**
     * Properties of entity PaymentBTCETHAddress.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "_id");
        public final static Property OwnerId = new Property(1, String.class, "ownerId", false, "OWNER_ID");
        public final static Property OwnerIsUser = new Property(2, boolean.class, "ownerIsUser", false, "OWNER_IS_USER");
        public final static Property CollectionType = new Property(3, String.class, "collectionType", false, "COLLECTION_TYPE");
        public final static Property CollectionAddr = new Property(4, String.class, "collectionAddr", false, "COLLECTION_ADDR");
        public final static Property Alias = new Property(5, String.class, "alias", false, "ALIAS");
        public final static Property BankName = new Property(6, String.class, "bankName", false, "BANK_NAME");
        public final static Property BranchName = new Property(7, String.class, "branchName", false, "BRANCH_NAME");
        public final static Property OwnerName = new Property(8, String.class, "ownerName", false, "OWNER_NAME");
        public final static Property Remark = new Property(9, String.class, "remark", false, "REMARK");
        public final static Property CreateTime = new Property(10, long.class, "createTime", false, "CREATE_TIME");
        public final static Property UpdateTime = new Property(11, long.class, "updateTime", false, "UPDATE_TIME");
        public final static Property IsDel = new Property(12, boolean.class, "isDel", false, "IS_DEL");
    }


    public PaymentBTCETHAddressDao(DaoConfig config) {
        super(config);
    }
    
    public PaymentBTCETHAddressDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PAYMENT_BTCETHADDRESS\" (" + //
                "\"_id\" INTEGER PRIMARY KEY NOT NULL ," + // 0: id
                "\"OWNER_ID\" TEXT," + // 1: ownerId
                "\"OWNER_IS_USER\" INTEGER NOT NULL ," + // 2: ownerIsUser
                "\"COLLECTION_TYPE\" TEXT," + // 3: collectionType
                "\"COLLECTION_ADDR\" TEXT," + // 4: collectionAddr
                "\"ALIAS\" TEXT," + // 5: alias
                "\"BANK_NAME\" TEXT," + // 6: bankName
                "\"BRANCH_NAME\" TEXT," + // 7: branchName
                "\"OWNER_NAME\" TEXT," + // 8: ownerName
                "\"REMARK\" TEXT," + // 9: remark
                "\"CREATE_TIME\" INTEGER NOT NULL ," + // 10: createTime
                "\"UPDATE_TIME\" INTEGER NOT NULL ," + // 11: updateTime
                "\"IS_DEL\" INTEGER NOT NULL );"); // 12: isDel
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PAYMENT_BTCETHADDRESS\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, PaymentBTCETHAddress entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String ownerId = entity.getOwnerId();
        if (ownerId != null) {
            stmt.bindString(2, ownerId);
        }
        stmt.bindLong(3, entity.getOwnerIsUser() ? 1L: 0L);
 
        String collectionType = entity.getCollectionType();
        if (collectionType != null) {
            stmt.bindString(4, collectionType);
        }
 
        String collectionAddr = entity.getCollectionAddr();
        if (collectionAddr != null) {
            stmt.bindString(5, collectionAddr);
        }
 
        String alias = entity.getAlias();
        if (alias != null) {
            stmt.bindString(6, alias);
        }
 
        String bankName = entity.getBankName();
        if (bankName != null) {
            stmt.bindString(7, bankName);
        }
 
        String branchName = entity.getBranchName();
        if (branchName != null) {
            stmt.bindString(8, branchName);
        }
 
        String ownerName = entity.getOwnerName();
        if (ownerName != null) {
            stmt.bindString(9, ownerName);
        }
 
        String remark = entity.getRemark();
        if (remark != null) {
            stmt.bindString(10, remark);
        }
        stmt.bindLong(11, entity.getCreateTime());
        stmt.bindLong(12, entity.getUpdateTime());
        stmt.bindLong(13, entity.getIsDel() ? 1L: 0L);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, PaymentBTCETHAddress entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String ownerId = entity.getOwnerId();
        if (ownerId != null) {
            stmt.bindString(2, ownerId);
        }
        stmt.bindLong(3, entity.getOwnerIsUser() ? 1L: 0L);
 
        String collectionType = entity.getCollectionType();
        if (collectionType != null) {
            stmt.bindString(4, collectionType);
        }
 
        String collectionAddr = entity.getCollectionAddr();
        if (collectionAddr != null) {
            stmt.bindString(5, collectionAddr);
        }
 
        String alias = entity.getAlias();
        if (alias != null) {
            stmt.bindString(6, alias);
        }
 
        String bankName = entity.getBankName();
        if (bankName != null) {
            stmt.bindString(7, bankName);
        }
 
        String branchName = entity.getBranchName();
        if (branchName != null) {
            stmt.bindString(8, branchName);
        }
 
        String ownerName = entity.getOwnerName();
        if (ownerName != null) {
            stmt.bindString(9, ownerName);
        }
 
        String remark = entity.getRemark();
        if (remark != null) {
            stmt.bindString(10, remark);
        }
        stmt.bindLong(11, entity.getCreateTime());
        stmt.bindLong(12, entity.getUpdateTime());
        stmt.bindLong(13, entity.getIsDel() ? 1L: 0L);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public PaymentBTCETHAddress readEntity(Cursor cursor, int offset) {
        PaymentBTCETHAddress entity = new PaymentBTCETHAddress( //
            cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // ownerId
            cursor.getShort(offset + 2) != 0, // ownerIsUser
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // collectionType
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // collectionAddr
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // alias
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // bankName
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // branchName
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // ownerName
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // remark
            cursor.getLong(offset + 10), // createTime
            cursor.getLong(offset + 11), // updateTime
            cursor.getShort(offset + 12) != 0 // isDel
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, PaymentBTCETHAddress entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setOwnerId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setOwnerIsUser(cursor.getShort(offset + 2) != 0);
        entity.setCollectionType(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setCollectionAddr(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setAlias(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setBankName(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setBranchName(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setOwnerName(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setRemark(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setCreateTime(cursor.getLong(offset + 10));
        entity.setUpdateTime(cursor.getLong(offset + 11));
        entity.setIsDel(cursor.getShort(offset + 12) != 0);
     }
    
    @Override
    protected final Long updateKeyAfterInsert(PaymentBTCETHAddress entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(PaymentBTCETHAddress entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(PaymentBTCETHAddress entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
