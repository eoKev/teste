package com.example.musicwhisky1.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.musicwhisky1.model.Artista;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
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
public final class ArtistaDao_Impl implements ArtistaDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Artista> __insertionAdapterOfArtista;

  private final EntityInsertionAdapter<Artista> __insertionAdapterOfArtista_1;

  private final EntityDeletionOrUpdateAdapter<Artista> __deletionAdapterOfArtista;

  private final EntityDeletionOrUpdateAdapter<Artista> __updateAdapterOfArtista;

  public ArtistaDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfArtista = new EntityInsertionAdapter<Artista>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `artistas` (`id`,`nome`) VALUES (nullif(?, 0),?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Artista entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getNome());
      }
    };
    this.__insertionAdapterOfArtista_1 = new EntityInsertionAdapter<Artista>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `artistas` (`id`,`nome`) VALUES (nullif(?, 0),?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Artista entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getNome());
      }
    };
    this.__deletionAdapterOfArtista = new EntityDeletionOrUpdateAdapter<Artista>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `artistas` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Artista entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfArtista = new EntityDeletionOrUpdateAdapter<Artista>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `artistas` SET `id` = ?,`nome` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Artista entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getNome());
        statement.bindLong(3, entity.getId());
      }
    };
  }

  @Override
  public Object inserir(final Artista artista, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfArtista.insert(artista);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insert(final List<Artista> artistas, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfArtista_1.insert(artistas);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deletar(final Artista artista, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfArtista.handle(artista);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object atualizar(final Artista artista, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfArtista.handle(artista);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<Artista> buscarPorId(final int idArtista) {
    final String _sql = "SELECT * FROM artistas WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, idArtista);
    return __db.getInvalidationTracker().createLiveData(new String[] {"artistas"}, false, new Callable<Artista>() {
      @Override
      @Nullable
      public Artista call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome");
          final Artista _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpNome;
            _tmpNome = _cursor.getString(_cursorIndexOfNome);
            _result = new Artista(_tmpId,_tmpNome);
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
  public Object buscarTodos(final Continuation<? super List<Artista>> $completion) {
    final String _sql = "SELECT * FROM artistas";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Artista>>() {
      @Override
      @NonNull
      public List<Artista> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome");
          final List<Artista> _result = new ArrayList<Artista>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Artista _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpNome;
            _tmpNome = _cursor.getString(_cursorIndexOfNome);
            _item = new Artista(_tmpId,_tmpNome);
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
  public LiveData<List<Artista>> buscarPorNome(final String nome) {
    final String _sql = "SELECT * FROM artistas WHERE nome LIKE '%' || ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, nome);
    return __db.getInvalidationTracker().createLiveData(new String[] {"artistas"}, false, new Callable<List<Artista>>() {
      @Override
      @Nullable
      public List<Artista> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome");
          final List<Artista> _result = new ArrayList<Artista>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Artista _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpNome;
            _tmpNome = _cursor.getString(_cursorIndexOfNome);
            _item = new Artista(_tmpId,_tmpNome);
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
}
