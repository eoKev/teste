package com.example.musicwhisky1.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.LongSparseArray;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.RelationUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.musicwhisky.Album;
import com.example.musicwhisky1.model.AlbumComMusicas;
import com.example.musicwhisky1.model.Musica;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AlbumDao_Impl implements AlbumDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Album> __insertionAdapterOfAlbum;

  private final EntityInsertionAdapter<Album> __insertionAdapterOfAlbum_1;

  private final EntityDeletionOrUpdateAdapter<Album> __deletionAdapterOfAlbum;

  private final EntityDeletionOrUpdateAdapter<Album> __updateAdapterOfAlbum;

  public AlbumDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAlbum = new EntityInsertionAdapter<Album>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `albuns` (`id`,`nome`,`quantidadeMusicas`,`dataLancamento`,`genero`,`idArtista`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Album entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getNome());
        statement.bindLong(3, entity.getQuantidadeMusicas());
        statement.bindString(4, entity.getDataLancamento());
        statement.bindString(5, entity.getGenero());
        statement.bindLong(6, entity.getIdArtista());
      }
    };
    this.__insertionAdapterOfAlbum_1 = new EntityInsertionAdapter<Album>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `albuns` (`id`,`nome`,`quantidadeMusicas`,`dataLancamento`,`genero`,`idArtista`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Album entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getNome());
        statement.bindLong(3, entity.getQuantidadeMusicas());
        statement.bindString(4, entity.getDataLancamento());
        statement.bindString(5, entity.getGenero());
        statement.bindLong(6, entity.getIdArtista());
      }
    };
    this.__deletionAdapterOfAlbum = new EntityDeletionOrUpdateAdapter<Album>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `albuns` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Album entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfAlbum = new EntityDeletionOrUpdateAdapter<Album>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `albuns` SET `id` = ?,`nome` = ?,`quantidadeMusicas` = ?,`dataLancamento` = ?,`genero` = ?,`idArtista` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Album entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getNome());
        statement.bindLong(3, entity.getQuantidadeMusicas());
        statement.bindString(4, entity.getDataLancamento());
        statement.bindString(5, entity.getGenero());
        statement.bindLong(6, entity.getIdArtista());
        statement.bindLong(7, entity.getId());
      }
    };
  }

  @Override
  public Object inserir(final Album album, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAlbum.insert(album);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insert(final List<Album> artistas, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAlbum_1.insert(artistas);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deletar(final Album album, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfAlbum.handle(album);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object atualizar(final Album album, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfAlbum.handle(album);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object buscarTodos(final Continuation<? super List<Album>> $completion) {
    final String _sql = "SELECT * FROM albuns";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Album>>() {
      @Override
      @NonNull
      public List<Album> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome");
          final int _cursorIndexOfQuantidadeMusicas = CursorUtil.getColumnIndexOrThrow(_cursor, "quantidadeMusicas");
          final int _cursorIndexOfDataLancamento = CursorUtil.getColumnIndexOrThrow(_cursor, "dataLancamento");
          final int _cursorIndexOfGenero = CursorUtil.getColumnIndexOrThrow(_cursor, "genero");
          final int _cursorIndexOfIdArtista = CursorUtil.getColumnIndexOrThrow(_cursor, "idArtista");
          final List<Album> _result = new ArrayList<Album>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Album _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpNome;
            _tmpNome = _cursor.getString(_cursorIndexOfNome);
            final int _tmpQuantidadeMusicas;
            _tmpQuantidadeMusicas = _cursor.getInt(_cursorIndexOfQuantidadeMusicas);
            final String _tmpDataLancamento;
            _tmpDataLancamento = _cursor.getString(_cursorIndexOfDataLancamento);
            final String _tmpGenero;
            _tmpGenero = _cursor.getString(_cursorIndexOfGenero);
            final int _tmpIdArtista;
            _tmpIdArtista = _cursor.getInt(_cursorIndexOfIdArtista);
            _item = new Album(_tmpId,_tmpNome,_tmpQuantidadeMusicas,_tmpDataLancamento,_tmpGenero,_tmpIdArtista);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<Album> listarPorId(final int albumId) {
    final String _sql = "SELECT * FROM albuns WHERE id =?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, albumId);
    return __db.getInvalidationTracker().createLiveData(new String[] {"albuns"}, false, new Callable<Album>() {
      @Override
      @Nullable
      public Album call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome");
          final int _cursorIndexOfQuantidadeMusicas = CursorUtil.getColumnIndexOrThrow(_cursor, "quantidadeMusicas");
          final int _cursorIndexOfDataLancamento = CursorUtil.getColumnIndexOrThrow(_cursor, "dataLancamento");
          final int _cursorIndexOfGenero = CursorUtil.getColumnIndexOrThrow(_cursor, "genero");
          final int _cursorIndexOfIdArtista = CursorUtil.getColumnIndexOrThrow(_cursor, "idArtista");
          final Album _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpNome;
            _tmpNome = _cursor.getString(_cursorIndexOfNome);
            final int _tmpQuantidadeMusicas;
            _tmpQuantidadeMusicas = _cursor.getInt(_cursorIndexOfQuantidadeMusicas);
            final String _tmpDataLancamento;
            _tmpDataLancamento = _cursor.getString(_cursorIndexOfDataLancamento);
            final String _tmpGenero;
            _tmpGenero = _cursor.getString(_cursorIndexOfGenero);
            final int _tmpIdArtista;
            _tmpIdArtista = _cursor.getInt(_cursorIndexOfIdArtista);
            _result = new Album(_tmpId,_tmpNome,_tmpQuantidadeMusicas,_tmpDataLancamento,_tmpGenero,_tmpIdArtista);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public List<AlbumComMusicas> listarAlbumComMusicas() {
    final String _sql = "SELECT * FROM albuns";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
      try {
        final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
        final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome");
        final int _cursorIndexOfQuantidadeMusicas = CursorUtil.getColumnIndexOrThrow(_cursor, "quantidadeMusicas");
        final int _cursorIndexOfDataLancamento = CursorUtil.getColumnIndexOrThrow(_cursor, "dataLancamento");
        final int _cursorIndexOfGenero = CursorUtil.getColumnIndexOrThrow(_cursor, "genero");
        final int _cursorIndexOfIdArtista = CursorUtil.getColumnIndexOrThrow(_cursor, "idArtista");
        final LongSparseArray<ArrayList<Musica>> _collectionMusicas = new LongSparseArray<ArrayList<Musica>>();
        while (_cursor.moveToNext()) {
          final long _tmpKey;
          _tmpKey = _cursor.getLong(_cursorIndexOfId);
          if (!_collectionMusicas.containsKey(_tmpKey)) {
            _collectionMusicas.put(_tmpKey, new ArrayList<Musica>());
          }
        }
        _cursor.moveToPosition(-1);
        __fetchRelationshipmusicasAscomExampleMusicwhisky1ModelMusica(_collectionMusicas);
        final List<AlbumComMusicas> _result = new ArrayList<AlbumComMusicas>(_cursor.getCount());
        while (_cursor.moveToNext()) {
          final AlbumComMusicas _item;
          final Album _tmpAlbum;
          final int _tmpId;
          _tmpId = _cursor.getInt(_cursorIndexOfId);
          final String _tmpNome;
          _tmpNome = _cursor.getString(_cursorIndexOfNome);
          final int _tmpQuantidadeMusicas;
          _tmpQuantidadeMusicas = _cursor.getInt(_cursorIndexOfQuantidadeMusicas);
          final String _tmpDataLancamento;
          _tmpDataLancamento = _cursor.getString(_cursorIndexOfDataLancamento);
          final String _tmpGenero;
          _tmpGenero = _cursor.getString(_cursorIndexOfGenero);
          final int _tmpIdArtista;
          _tmpIdArtista = _cursor.getInt(_cursorIndexOfIdArtista);
          _tmpAlbum = new Album(_tmpId,_tmpNome,_tmpQuantidadeMusicas,_tmpDataLancamento,_tmpGenero,_tmpIdArtista);
          final ArrayList<Musica> _tmpMusicasCollection;
          final long _tmpKey_1;
          _tmpKey_1 = _cursor.getLong(_cursorIndexOfId);
          _tmpMusicasCollection = _collectionMusicas.get(_tmpKey_1);
          _item = new AlbumComMusicas(_tmpAlbum,_tmpMusicasCollection);
          _result.add(_item);
        }
        __db.setTransactionSuccessful();
        return _result;
      } finally {
        _cursor.close();
        _statement.release();
      }
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<Album> buscarPorId(final int id) {
    final String _sql = "SELECT * FROM albuns WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return __db.getInvalidationTracker().createLiveData(new String[] {"albuns"}, false, new Callable<Album>() {
      @Override
      @Nullable
      public Album call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome");
          final int _cursorIndexOfQuantidadeMusicas = CursorUtil.getColumnIndexOrThrow(_cursor, "quantidadeMusicas");
          final int _cursorIndexOfDataLancamento = CursorUtil.getColumnIndexOrThrow(_cursor, "dataLancamento");
          final int _cursorIndexOfGenero = CursorUtil.getColumnIndexOrThrow(_cursor, "genero");
          final int _cursorIndexOfIdArtista = CursorUtil.getColumnIndexOrThrow(_cursor, "idArtista");
          final Album _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpNome;
            _tmpNome = _cursor.getString(_cursorIndexOfNome);
            final int _tmpQuantidadeMusicas;
            _tmpQuantidadeMusicas = _cursor.getInt(_cursorIndexOfQuantidadeMusicas);
            final String _tmpDataLancamento;
            _tmpDataLancamento = _cursor.getString(_cursorIndexOfDataLancamento);
            final String _tmpGenero;
            _tmpGenero = _cursor.getString(_cursorIndexOfGenero);
            final int _tmpIdArtista;
            _tmpIdArtista = _cursor.getInt(_cursorIndexOfIdArtista);
            _result = new Album(_tmpId,_tmpNome,_tmpQuantidadeMusicas,_tmpDataLancamento,_tmpGenero,_tmpIdArtista);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<Album>> buscarPorNome(final String nome) {
    final String _sql = "SELECT * FROM albuns WHERE nome LIKE '%' || ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, nome);
    return __db.getInvalidationTracker().createLiveData(new String[] {"albuns"}, false, new Callable<List<Album>>() {
      @Override
      @Nullable
      public List<Album> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome");
          final int _cursorIndexOfQuantidadeMusicas = CursorUtil.getColumnIndexOrThrow(_cursor, "quantidadeMusicas");
          final int _cursorIndexOfDataLancamento = CursorUtil.getColumnIndexOrThrow(_cursor, "dataLancamento");
          final int _cursorIndexOfGenero = CursorUtil.getColumnIndexOrThrow(_cursor, "genero");
          final int _cursorIndexOfIdArtista = CursorUtil.getColumnIndexOrThrow(_cursor, "idArtista");
          final List<Album> _result = new ArrayList<Album>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Album _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpNome;
            _tmpNome = _cursor.getString(_cursorIndexOfNome);
            final int _tmpQuantidadeMusicas;
            _tmpQuantidadeMusicas = _cursor.getInt(_cursorIndexOfQuantidadeMusicas);
            final String _tmpDataLancamento;
            _tmpDataLancamento = _cursor.getString(_cursorIndexOfDataLancamento);
            final String _tmpGenero;
            _tmpGenero = _cursor.getString(_cursorIndexOfGenero);
            final int _tmpIdArtista;
            _tmpIdArtista = _cursor.getInt(_cursorIndexOfIdArtista);
            _item = new Album(_tmpId,_tmpNome,_tmpQuantidadeMusicas,_tmpDataLancamento,_tmpGenero,_tmpIdArtista);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private void __fetchRelationshipmusicasAscomExampleMusicwhisky1ModelMusica(
      @NonNull final LongSparseArray<ArrayList<Musica>> _map) {
    if (_map.isEmpty()) {
      return;
    }
    if (_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      RelationUtil.recursiveFetchLongSparseArray(_map, true, (map) -> {
        __fetchRelationshipmusicasAscomExampleMusicwhisky1ModelMusica(map);
        return Unit.INSTANCE;
      });
      return;
    }
    final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `id`,`idAlbum`,`idArtista`,`nome`,`duracao` FROM `musicas` WHERE `idAlbum` IN (");
    final int _inputSize = _map.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int i = 0; i < _map.size(); i++) {
      final long _item = _map.keyAt(i);
      _stmt.bindLong(_argIndex, _item);
      _argIndex++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "idAlbum");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfId = 0;
      final int _cursorIndexOfIdAlbum = 1;
      final int _cursorIndexOfIdArtista = 2;
      final int _cursorIndexOfNome = 3;
      final int _cursorIndexOfDuracao = 4;
      while (_cursor.moveToNext()) {
        final long _tmpKey;
        _tmpKey = _cursor.getLong(_itemKeyIndex);
        final ArrayList<Musica> _tmpRelation = _map.get(_tmpKey);
        if (_tmpRelation != null) {
          final Musica _item_1;
          final int _tmpId;
          _tmpId = _cursor.getInt(_cursorIndexOfId);
          final int _tmpIdAlbum;
          _tmpIdAlbum = _cursor.getInt(_cursorIndexOfIdAlbum);
          final int _tmpIdArtista;
          _tmpIdArtista = _cursor.getInt(_cursorIndexOfIdArtista);
          final String _tmpNome;
          _tmpNome = _cursor.getString(_cursorIndexOfNome);
          final int _tmpDuracao;
          _tmpDuracao = _cursor.getInt(_cursorIndexOfDuracao);
          _item_1 = new Musica(_tmpId,_tmpIdAlbum,_tmpIdArtista,_tmpNome,_tmpDuracao);
          _tmpRelation.add(_item_1);
        }
      }
    } finally {
      _cursor.close();
    }
  }
}
