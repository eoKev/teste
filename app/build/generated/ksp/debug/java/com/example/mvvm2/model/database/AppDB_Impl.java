package com.example.mvvm2.model.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.example.musicwhisky1.dao.AlbumDao;
import com.example.musicwhisky1.dao.AlbumDao_Impl;
import com.example.musicwhisky1.dao.ArtistaDao;
import com.example.musicwhisky1.dao.ArtistaDao_Impl;
import com.example.musicwhisky1.dao.MusicaDao;
import com.example.musicwhisky1.dao.MusicaDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDB_Impl extends AppDB {
  private volatile ArtistaDao _artistaDao;

  private volatile MusicaDao _musicaDao;

  private volatile AlbumDao _albumDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(3) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `artistas` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nome` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `musicas` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `idAlbum` INTEGER NOT NULL, `idArtista` INTEGER NOT NULL, `nome` TEXT NOT NULL, `duracao` INTEGER NOT NULL, FOREIGN KEY(`idAlbum`) REFERENCES `albuns`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`idArtista`) REFERENCES `artistas`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_musicas_idAlbum` ON `musicas` (`idAlbum`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_musicas_idArtista` ON `musicas` (`idArtista`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `albuns` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nome` TEXT NOT NULL, `quantidadeMusicas` INTEGER NOT NULL, `dataLancamento` TEXT NOT NULL, `genero` TEXT NOT NULL, `idArtista` INTEGER NOT NULL, FOREIGN KEY(`idArtista`) REFERENCES `artistas`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_albuns_idArtista` ON `albuns` (`idArtista`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'f439c6461b5cec56b14f2f2b96bc91f7')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `artistas`");
        db.execSQL("DROP TABLE IF EXISTS `musicas`");
        db.execSQL("DROP TABLE IF EXISTS `albuns`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsArtistas = new HashMap<String, TableInfo.Column>(2);
        _columnsArtistas.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArtistas.put("nome", new TableInfo.Column("nome", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysArtistas = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesArtistas = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoArtistas = new TableInfo("artistas", _columnsArtistas, _foreignKeysArtistas, _indicesArtistas);
        final TableInfo _existingArtistas = TableInfo.read(db, "artistas");
        if (!_infoArtistas.equals(_existingArtistas)) {
          return new RoomOpenHelper.ValidationResult(false, "artistas(com.example.musicwhisky1.model.Artista).\n"
                  + " Expected:\n" + _infoArtistas + "\n"
                  + " Found:\n" + _existingArtistas);
        }
        final HashMap<String, TableInfo.Column> _columnsMusicas = new HashMap<String, TableInfo.Column>(5);
        _columnsMusicas.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMusicas.put("idAlbum", new TableInfo.Column("idAlbum", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMusicas.put("idArtista", new TableInfo.Column("idArtista", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMusicas.put("nome", new TableInfo.Column("nome", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMusicas.put("duracao", new TableInfo.Column("duracao", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMusicas = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysMusicas.add(new TableInfo.ForeignKey("albuns", "CASCADE", "NO ACTION", Arrays.asList("idAlbum"), Arrays.asList("id")));
        _foreignKeysMusicas.add(new TableInfo.ForeignKey("artistas", "CASCADE", "NO ACTION", Arrays.asList("idArtista"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesMusicas = new HashSet<TableInfo.Index>(2);
        _indicesMusicas.add(new TableInfo.Index("index_musicas_idAlbum", false, Arrays.asList("idAlbum"), Arrays.asList("ASC")));
        _indicesMusicas.add(new TableInfo.Index("index_musicas_idArtista", false, Arrays.asList("idArtista"), Arrays.asList("ASC")));
        final TableInfo _infoMusicas = new TableInfo("musicas", _columnsMusicas, _foreignKeysMusicas, _indicesMusicas);
        final TableInfo _existingMusicas = TableInfo.read(db, "musicas");
        if (!_infoMusicas.equals(_existingMusicas)) {
          return new RoomOpenHelper.ValidationResult(false, "musicas(com.example.musicwhisky1.model.Musica).\n"
                  + " Expected:\n" + _infoMusicas + "\n"
                  + " Found:\n" + _existingMusicas);
        }
        final HashMap<String, TableInfo.Column> _columnsAlbuns = new HashMap<String, TableInfo.Column>(6);
        _columnsAlbuns.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlbuns.put("nome", new TableInfo.Column("nome", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlbuns.put("quantidadeMusicas", new TableInfo.Column("quantidadeMusicas", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlbuns.put("dataLancamento", new TableInfo.Column("dataLancamento", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlbuns.put("genero", new TableInfo.Column("genero", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlbuns.put("idArtista", new TableInfo.Column("idArtista", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAlbuns = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysAlbuns.add(new TableInfo.ForeignKey("artistas", "CASCADE", "NO ACTION", Arrays.asList("idArtista"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesAlbuns = new HashSet<TableInfo.Index>(1);
        _indicesAlbuns.add(new TableInfo.Index("index_albuns_idArtista", false, Arrays.asList("idArtista"), Arrays.asList("ASC")));
        final TableInfo _infoAlbuns = new TableInfo("albuns", _columnsAlbuns, _foreignKeysAlbuns, _indicesAlbuns);
        final TableInfo _existingAlbuns = TableInfo.read(db, "albuns");
        if (!_infoAlbuns.equals(_existingAlbuns)) {
          return new RoomOpenHelper.ValidationResult(false, "albuns(com.example.musicwhisky.Album).\n"
                  + " Expected:\n" + _infoAlbuns + "\n"
                  + " Found:\n" + _existingAlbuns);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "f439c6461b5cec56b14f2f2b96bc91f7", "5db4a1a4644c5c3e422360d422ba3787");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "artistas","musicas","albuns");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `artistas`");
      _db.execSQL("DELETE FROM `musicas`");
      _db.execSQL("DELETE FROM `albuns`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(ArtistaDao.class, ArtistaDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(MusicaDao.class, MusicaDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AlbumDao.class, AlbumDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public ArtistaDao artistaDao() {
    if (_artistaDao != null) {
      return _artistaDao;
    } else {
      synchronized(this) {
        if(_artistaDao == null) {
          _artistaDao = new ArtistaDao_Impl(this);
        }
        return _artistaDao;
      }
    }
  }

  @Override
  public MusicaDao musicaDao() {
    if (_musicaDao != null) {
      return _musicaDao;
    } else {
      synchronized(this) {
        if(_musicaDao == null) {
          _musicaDao = new MusicaDao_Impl(this);
        }
        return _musicaDao;
      }
    }
  }

  @Override
  public AlbumDao albumDao() {
    if (_albumDao != null) {
      return _albumDao;
    } else {
      synchronized(this) {
        if(_albumDao == null) {
          _albumDao = new AlbumDao_Impl(this);
        }
        return _albumDao;
      }
    }
  }
}
